package com.mobile.fairless.features.main.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.pagination.Pager
import com.mobile.fairless.common.pagination.PagingData
import com.mobile.fairless.common.storage.PrefService
import com.mobile.fairless.common.utils.UrlEncode
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.ProductData
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
    fun getProductsByCategory()
    fun selectCategory(category: Category)
    fun onDocumentClick(product: String)
    fun onProfileClick()
    fun onAppend()
    fun onRefresh()
}

data class ProductModel(
    val product: ProductData,
    val category: String
)

class MainViewModelImpl(override val navigator: Navigator) : KmpViewModelImpl(), KoinComponent,
    MainViewModel {

    private val mainService: MainService by inject()
    private val errorService: ErrorService by inject()
    private val prefService: PrefService by inject()
    private val urlEncode: UrlEncode by inject()

    private val _state = MutableStateFlow(MainState())
    override val state: StateFlow<MainState> = _state.asStateFlow()

    private val pager = Pager<ProductData>(mainService)

    override val statePaging: StateFlow<MainState> =
        pager.state.map { pagingData ->
            val list = pagingData.data.map {
                ProductModel(it, state.value.selectCategory.type ?: "consoles")
            }.toMutableList()
            MainState(
                PagingData<ProductModel>(
                    loadingState = pagingData.loadingState,
                    isRefreshing = pagingData.isRefreshing,
                    isAppending = pagingData.isAppending,
                    data = list,
                    category = state.value.selectCategory.type ?: "consoles"
                )
            )
        }.stateIn(scope, SharingStarted.WhileSubscribed(), MainState())

    override fun onAppend() {
        pager.onAppend()
    }

    override fun onRefresh() {
        pager.onRefresh()
    }

    override fun onViewShown() {
        super.onViewShown()
        getCategories()
//        if (_state.value.products.data == null) {
//            getProductsByCategory()
//        }
    }

    override fun onViewHidden() {
        super.onViewHidden()
        pager.onViewHidden()
    }

    override fun getCategories() {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    _state.update { it.copy(categoriesLoading = true) }
                    if (_state.value.categories == null) {
                        val categories = mainService.getCategories().toMutableList()
                        val indexAllCategory = categories.indexOfFirst { it.url == "all" }

                        if (indexAllCategory != -1) { // Перенос "Все категории" на первую позицию в списке
                            val elementToMove = categories[indexAllCategory]
                            categories.removeAt(indexAllCategory)
                            categories.add(0, elementToMove)
                        }
                        _state.update { it.copy(categories = categories) }
                    }
                },
                failureBlock = {
                    errorService.showError("Ошибка загрузки. Проверьте подключение к сети и повторите попытку.")
                },
                completionBlock = {
                    _state.update { it.copy(categoriesLoading = false) }
                }
            )
        }
    }

    override fun getProductsByCategory() {
//        jobs.add(
//            scope.launch {
//                exceptionHandleable(
//                    executionBlock = {
//                        val data = ProductModel(mainService.getProductsByCategory: "all"))
//
//                    }
//                )
//            }
//        )
    }

    override fun selectCategory(category: Category) {
        pager.updateCategory(category.type?: "all")
        _state.update { it.copy(selectCategory = category) }
//        getProductsByCategory()
        println("asdasd    ${state.value.selectCategory.type}")
        pager.onRefresh()
        //pager = Pager<ProductData>(state.value.selectCategory.type ?: "all", mainService)
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

    private fun setLoading(isLoading: Boolean) {
        _state.update { it.copy(productsLoading = isLoading) }
    }
}
