package com.mobile.fairless.features.search.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.utils.UrlEncode
import com.mobile.fairless.common.viewModel.StatefulKmpViewModel
import com.mobile.fairless.common.viewModel.StatefulKmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.search.service.SearchService
import com.mobile.fairless.features.search.state.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface SearchViewModel : StatefulKmpViewModel<SearchState>, SubScreenViewModel {
    override val state: StateFlow<SearchState>

    fun searchProducts(name: String)
    fun searchChanged(search: String)
    fun onDeleteSearchClick()
    fun selectPopularFilter(name: String)
    fun selectFilters(name: String)
    fun popularFilterOpen()
    fun filtersOpen()
    fun onDocumentClick(product: ProductData)
    fun getCategories()
}

class SearchViewModelImpl(override val navigator: Navigator) : KoinComponent, StatefulKmpViewModelImpl<SearchState>(),
    SearchViewModel {

    private val searchService: SearchService by inject()
    private val errorService: ErrorService by inject()
    private val urlEncode: UrlEncode by inject()

    private val _state = MutableStateFlow(SearchState())
    override val state: StateFlow<SearchState> = _state.asStateFlow()

    override fun onViewShown() {
        super.onViewShown()
        getCategories()
    }

    override fun searchProducts(name: String) {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    setLoading(true)
                    _state.update { it.copy(products = searchService.searchProducts(name)) }
                    println("kjhsdkjfhkjsdf        ||       $name")
                    setLoading(false)
                },
                failureBlock = {
                    errorService.showError("Ошибка с подключение с интернетом")
                }
            )
        }
    }

    override fun searchChanged(search: String) {
        _state.update { it.copy(searchString = search) }
    }

    override fun onDeleteSearchClick() {
        searchChanged("")
    }

    override fun selectPopularFilter(name: String) {
        _state.update { it.copy(selectedPopularFilter = name) }
    }

    override fun selectFilters(name: String) {
        _state.update { it.copy(selectedFilters = name) }
    }

    override fun popularFilterOpen() {
        _state.update { it.copy(popularFilterOpen = !it.popularFilterOpen) }
    }

    override fun filtersOpen() {
        _state.update { it.copy(filtersOpen = !it.filtersOpen) }
    }

    override fun onDocumentClick(product: ProductData) {
        val document = Json.encodeToString(product)
        val encodeUrl = urlEncode.encodeToUrl(document)
        navigator.navigateToDocument(encodeUrl)
    }

    override fun getCategories() {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    _state.update { it.copy(categoriesLoading = true) }
                    if (_state.value.categories == null) {
                        val categories = searchService.getCategories().toMutableList()
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

    private fun setLoading(status: Boolean){
        _state.update { it.copy(productsLoading = status) }
    }
}