package com.mobile.fairless.features.shop.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.pagination.Pager
import com.mobile.fairless.common.pagination.PaginationType
import com.mobile.fairless.common.pagination.PagingData
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.common.utils.UrlEncode
import com.mobile.fairless.common.viewModel.StatefulKmpViewModel
import com.mobile.fairless.common.viewModel.StatefulKmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.Shop
import com.mobile.fairless.features.main.models.Type
import com.mobile.fairless.features.main.viewModel.ProductModel
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.search.models.PopularFilter
import com.mobile.fairless.features.shop.service.ShopService
import com.mobile.fairless.features.shop.state.ShopState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ShopViewModel : StatefulKmpViewModel<ShopState>, SubScreenViewModel {
    override val state: StateFlow<ShopState>
    val statePaging: StateFlow<ShopState>

    fun getMainShop(shop: String)
    fun getShop(code: Shop)
    fun getShops()
    fun selectPopularFilter(popularFilter: PopularFilter)
    fun popularFilterOpen()
    fun filtersOpen()
    fun onDocumentClick(product: String)
    fun getCategories()
    fun selectCategory(category: Category)
    fun onAppend()
    fun onRefresh()
    fun selectType(type: Type)
    fun categoryDropDownMenuOpen()
    fun onRefreshClick()
    fun typeFilterOpen()
    fun searchChanged(search: String)
    fun onDeleteSearchClick()
}

class ShopViewModelImpl(override val navigator: Navigator) : KoinComponent,
    StatefulKmpViewModelImpl<ShopState>(),
    ShopViewModel {

    private val shopService: ShopService by inject()
    private val urlEncode: UrlEncode by inject()

    private val _state = MutableStateFlow(ShopState())
    override val state: StateFlow<ShopState> = _state.asStateFlow()

    private val pager = Pager(PaginationType.SHOP, shopService)

    override val statePaging: StateFlow<ShopState> =
        pager.state.map { pagingData ->
            val list = pagingData.data.map {
                ProductModel(it, state.value.selectCategory.type ?: "news")
            }.toMutableList()
            ShopState(
                PagingData<ProductModel>(
                    loadingState = pagingData.loadingState,
                    isRefreshing = pagingData.isRefreshing,
                    isAppending = pagingData.isAppending,
                    data = list,
                    category = state.value.selectCategory.type ?: "news",
                    type = state.value.selectType.type,
                    sort = state.value.selectedPopularFilter.sort,
                    shop = state.value.shop?.get(0),
                )
            )
        }.stateIn(scope, SharingStarted.WhileSubscribed(), ShopState())

    override fun getMainShop(shop: String) {
        scope.launch {
            val decodeShop = urlEncode.decodeToUrl(shop)
            _state.update { it.copy(shop = listOf(Json.decodeFromString(decodeShop))) }
            pager.changeShop(state.value.shop?.get(0) ?: listOf(Shop())[0])
        }
    }

    override fun getShop(code: Shop) {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    _state.update { it.copy(shop = shopService.getShop(code.code ?: "")) }
                    if (state.value.products == null){
                        pager.changeShop(state.value.shop?.get(0) ?: listOf(Shop())[0])
                    }
                },
            )
        }
    }

    override fun getShops() {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    _state.update {
                        it.copy(
                            shopList = shopService.getShops(),
                            shopsLoadingState = LoadingState.Success
                        )
                    }
                },
                failureBlock = {
                    _state.update { it.copy(shopsLoadingState = LoadingState.Error(it.toString())) }
                }
            )
        }
    }

    override fun onViewShown() {
        super.onViewShown()
        getCategories()
    }

    override fun selectPopularFilter(popularFilter: PopularFilter) {
        _state.update { it.copy(selectedPopularFilter = popularFilter) }
        pager.changeFilter(popularFilter.sort)
    }

    override fun popularFilterOpen() {
        _state.update { it.copy(popularFilterOpen = !it.popularFilterOpen) }
    }

    override fun filtersOpen() {
        _state.update { it.copy(filtersOpen = !it.filtersOpen) }
    }

    override fun onDocumentClick(product: String) {
        val document = Json.encodeToString(product)
        val encodeUrl = urlEncode.encodeToUrl(document)
        navigator.navigateToDocument(encodeUrl)
    }

    override fun getCategories() {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    if (_state.value.categories == null) {
                        val categories = shopService.getCategories().toMutableList()
                        val indexAllCategory = categories.indexOfFirst { it.url == "all" }

                        if (indexAllCategory != -1) { // Перенос "Все категории" на первую позицию в списке
                            val elementToMove = categories[indexAllCategory]
                            categories.removeAt(indexAllCategory)
                            categories.add(0, elementToMove)
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
        pager.updateCategory(category.type ?: "all")
    }

    override fun onAppend() {
        pager.onAppend()
    }

    override fun onRefresh() {
        setLoadingRefreshable(true)
        pager.onRefresh()
        setLoadingRefreshable(false)
    }

    override fun selectType(type: Type) {
        _state.update { it.copy(selectType = type) }
        pager.changeType(type.type)
    }

    override fun categoryDropDownMenuOpen() {
        _state.update { it.copy(categoryOpen = !it.categoryOpen) }
    }

    override fun onRefreshClick() {
        getCategories()
        pager.onRefresh()
    }

    override fun typeFilterOpen() {
        _state.update { it.copy(typeFilterOpen = !it.typeFilterOpen) }
    }

    override fun searchChanged(search: String) {
        _state.update { it.copy(searchString = search) }
        pager.updateSearchText(state.value.searchString)
    }

    override fun onDeleteSearchClick() {
        searchChanged("")
    }

    private fun setLoadingRefreshable(status: Boolean) {
        _state.update { it.copy(refreshable = status) }
    }
}