package com.mobile.fairless.features.main.viewModel

import com.mobile.fairless.common.analytics.appmetrica.AppMetricaService
import com.mobile.fairless.common.analytics.appmetrica.LogEvent
import com.mobile.fairless.common.analytics.appmetrica.LogEventParam
import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.pagination.Pager
import com.mobile.fairless.common.pagination.PaginationType
import com.mobile.fairless.common.pagination.PagingData
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.common.storage.PrefService
import com.mobile.fairless.common.utils.UrlEncode
import com.mobile.fairless.common.viewModel.StatefulKmpViewModel
import com.mobile.fairless.common.viewModel.StatefulKmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.main.models.CategoryModel
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.models.Type
import com.mobile.fairless.features.main.service.MainService
import com.mobile.fairless.features.main.state.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface MainViewModel : StatefulKmpViewModel<MainState>, SubScreenViewModel {
    val statePaging: StateFlow<MainState>

    fun getCategories()
    fun selectCategory(category: CategoryModel)
    fun onDocumentClick(product: String, ios: Boolean = false)
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

class MainViewModelImpl(override val navigator: Navigator) : StatefulKmpViewModelImpl<MainState>(),
    KoinComponent,
    MainViewModel {

    private val mainService: MainService by inject()
    private val prefService: PrefService by inject()
    private val urlEncode: UrlEncode by inject()
    private val appMetricaService: AppMetricaService by inject()

    private val mutableState = MutableStateFlow(MainState())
    override val state: StateFlow<MainState> = mutableState.asStateFlow()

    private val pager = Pager<ProductData>(PaginationType.MAIN, mainService)

    override val statePaging: StateFlow<MainState> =
        pager.state.combine(mutableState) {pagingData, screenData ->
            val list = pagingData.data.map {
                ProductModel(it, state.value.selectCategory.type ?: "news")
            }
//            if (list.size < 30 && !pagingData.isAtEnd) onAppend()
            MainState(
                pagingData = PagingData(
                    loadingState = pagingData.loadingState,
                    isRefreshing = pagingData.isRefreshing,
                    isAppending = pagingData.isAppending,
                    data = list.toMutableList(),
                    category = state.value.selectCategory.type ?: "news",
                    type = state.value.selectType.type
                )
            )
        }.stateIn(scope, SharingStarted.WhileSubscribed(), MainState())
//        pager.state.map { data ->
//            val list = data.data.map {
//                ProductModel(it, state.value.selectCategory.type ?: "news")
//            }.toMutableList()
//            PagingData<ProductModel>(
//                loadingState = data.loadingState,
//                isRefreshing = data.isRefreshing,
//                isAppending = data.isAppending,
//                data = list,
//                category = state.value.selectCategory.type ?: "news",
//                type = state.value.selectType.type
//            )
//        }.combine(mutableState) { pagingData, screenData ->
//            MainState(
//                pagingData = pagingData
//            )
//        }.stateIn(scope, SharingStarted.WhileSubscribed(), MainState())

    init {
        scope.launch {
            statePaging.collect { item ->
                mutableState.update {
                    it.copy(
                        productsLoading = item.pagingData.loadingState,
                        products = item.pagingData.data,
                        isAppending = item.pagingData.isAppending
                    )
                }
            }
        }
    }

    override fun onViewShown() {
        super.onViewShown()
        getCategories()
//        if (state.value.pagingData.data.isEmpty()) {
//            pager.onRefresh()
//        } //TODO fix bag
        appMetricaService.sendEvent(
            LogEvent.OPEN_SCREEN, mapOf(
                LogEventParam.SCREEN_NAME to "Главная",
                LogEventParam.SCREEN_CLASS to "MainScree",
            )
        )
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
        mutableState.update { it.copy(selectType = type) }
        pager.changeType(type.type)
    }

    override fun authDialogOpen() {
        mutableState.update { it.copy(authDialogOpen = !it.authDialogOpen) }
    }

    private fun setLoadingRefreshable(status: Boolean) {
        mutableState.update { it.copy(refreshable = status) }
    }

    override fun getCategories() {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    if (mutableState.value.categories == null) {
                        mutableState.update { it.copy(categoriesLoading = LoadingState.Loading) }
                        val categories = mainService.getCategories().toMutableList()
                        categories.add(
                            0,
                            CategoryModel(name = "Новое", type = "news", url = "news")
                        )
                        val indexAllCategory = categories.indexOfFirst { it.url == "all" }

                        if (indexAllCategory != -1) { // Перенос "Все категории" на первую позицию в списке
                            val elementToMove = categories[indexAllCategory]
                            categories.removeAt(indexAllCategory)
                            categories.add(1, elementToMove)
                        }
                        mutableState.update { it.copy(categories = categories) }
                        mutableState.update { it.copy(categoriesLoading = LoadingState.Success) }
                    }
                },
                failureBlock = { throwable ->
                    mutableState.update {
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

    override fun selectCategory(category: CategoryModel) {
        mutableState.update { it.copy(selectCategory = category) }
        if (state.value.selectCategory.name != "Новое") {
            pager.updateCategory(category.type ?: "news")
        } else {
            pager.updateCategory(category.type ?: "news")
        }
    }

    override fun onDocumentClick(product: String, ios: Boolean) {
        if (!ios) {
            val document = Json.encodeToString(product)
            val encodeUrl = urlEncode.encodeToUrl(document)
            navigator.navigateToDocument(encodeUrl)
        } else {
            navigator.navigateToDocument(product)
        }
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
