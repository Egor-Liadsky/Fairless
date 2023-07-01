package com.mobile.fairless.features.main.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.pagination.Pager
import com.mobile.fairless.common.pagination.PagingData
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.common.storage.PrefService
import com.mobile.fairless.common.utils.UrlEncode
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.models.ProductStockType
import com.mobile.fairless.features.main.models.Type
import com.mobile.fairless.features.main.service.MainService
import com.mobile.fairless.features.main.state.MainState
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface MainViewModel : KmpViewModel, SubScreenViewModel {
    val statePaging: StateFlow<MainState>
    val state: StateFlow<MainState>

    fun getCategories()
    fun selectCategory(category: Category)
    fun onDocumentClick(product: String)
    fun onProfileClick()
    fun onAppend()
    fun onRefresh()
    fun onRefreshClick()
    fun selectType(type: Type)
    fun authDialogOpen()
}

data class ProductModel(
    val product: ProductData,
    val category: String
)

class MainViewModelImpl(override val navigator: Navigator) : KmpViewModelImpl(), KoinComponent,
    MainViewModel {

    private val mainService: MainService by inject()
    private val prefService: PrefService by inject()
    private val urlEncode: UrlEncode by inject()

    private val _state = MutableStateFlow(MainState())
    override val state: StateFlow<MainState> = _state.asStateFlow()

    private val pager = Pager<ProductData>(true, mainService)

    override val statePaging: StateFlow<MainState> =
        pager.state.map { pagingData ->
            val list = pagingData.data.map {
                ProductModel(it, state.value.selectCategory.type ?: "news")
            }.toMutableList()
            MainState(
                PagingData<ProductModel>(
                    loadingState = pagingData.loadingState,
                    isRefreshing = pagingData.isRefreshing,
                    isAppending = pagingData.isAppending,
                    data = list,
                    category = state.value.selectCategory.type ?: "news",
                    type = state.value.selectType.type
                )
            )
        }.stateIn(scope, SharingStarted.WhileSubscribed(), MainState())

    override fun onViewShown() {
        super.onViewShown()
        getCategories()
        if (statePaging.value.pagingData.data.isEmpty()){
            pager.onRefresh()
        }
    }

    override fun onViewHidden() {
        super.onViewHidden()
        pager.onViewHidden()
    }

    override fun onAppend() {
        pager.onAppend()
    }

    override fun onRefresh() {
        setLoadingRefreshable(true)
        pager.onRefresh()
        setLoadingRefreshable(false)
    }

    override fun onRefreshClick() {
        getCategories()
        pager.onRefresh()
    }

    override fun selectType(type: Type) {
        _state.update { it.copy(selectType = type) }
        pager.changeType(type.type)
    }

    override fun authDialogOpen() {
        _state.update { it.copy(authDialogOpen = !it.authDialogOpen) }
    }

    private fun setLoadingRefreshable(status: Boolean) {
        _state.update { it.copy(refreshable = status) }
    }

    override fun getCategories() {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    if (_state.value.categories == null) {
                        _state.update { it.copy(categoriesLoading = LoadingState.Loading) }
                        val categories = mainService.getCategories().toMutableList()
                        categories.add(0, Category(name = "Новое", type = "news", url = "news"))
                        val indexAllCategory = categories.indexOfFirst { it.url == "all" }

                        if (indexAllCategory != -1) { // Перенос "Все категории" на первую позицию в списке
                            val elementToMove = categories[indexAllCategory]
                            categories.removeAt(indexAllCategory)
                            categories.add(1, elementToMove)
                        }
                        _state.update { it.copy(categories = categories) }
                        _state.update { it.copy(categoriesLoading = LoadingState.Success) }
                    }
                },
                failureBlock = { throwable ->
                    _state.update {
                        it.copy(
                            categoriesLoading = LoadingState.Error(
                                throwable.message ?: "error"
                            )
                        )
                    }
                }
            )
        }
    }

    override fun selectCategory(category: Category) {
        _state.update { it.copy(selectCategory = category) }
        if (state.value.selectCategory.name != "Новое") {
            pager.updateCategory(category.type ?: "news")
        } else {
            pager.updateCategory(category.type ?: "news")
        }
    }

    override fun onDocumentClick(product: String) {
        val document = Json.encodeToString(product)
        val encodeUrl = urlEncode.encodeToUrl(document)
        navigator.navigateToDocument(encodeUrl)
    }

    override fun onProfileClick() {
        val data = prefService.getUserInfo()
        if (data?.user == null) {
            navigator.navigateToAuth()
        } else {
            navigator.navigateToProfile()
        }
    }
}
