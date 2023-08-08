package com.mobile.fairless.features.search.viewModel

import com.mobile.fairless.common.analytics.appmetrica.AppMetricaService
import com.mobile.fairless.common.analytics.appmetrica.LogEvent
import com.mobile.fairless.common.analytics.appmetrica.LogEventParam
import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.pagination.Pager
import com.mobile.fairless.common.pagination.PaginationType
import com.mobile.fairless.common.pagination.PagingData
import com.mobile.fairless.common.utils.UrlEncode
import com.mobile.fairless.common.viewModel.StatefulKmpViewModel
import com.mobile.fairless.common.viewModel.StatefulKmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.main.models.CategoryModel
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.models.Type
import com.mobile.fairless.features.main.viewModel.ProductModel
import com.mobile.fairless.features.search.models.PopularFilter
import com.mobile.fairless.features.search.service.SearchService
import com.mobile.fairless.features.search.state.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface SearchViewModel : StatefulKmpViewModel<SearchState>, SubScreenViewModel {
    override val state: StateFlow<SearchState>
    val statePaging: StateFlow<SearchState>

    fun searchChanged(search: String)
    fun onDeleteSearchClick()
    fun selectPopularFilter(popularFilter: PopularFilter)
    fun popularFilterOpen()
    fun filtersOpen()
    fun onDocumentClick(product: String)
    fun selectCategory(category: CategoryModel)
    fun onAppend()
    fun onRefresh()
    fun selectType(type: Type)
}

class SearchViewModelImpl(override val navigator: Navigator) : KoinComponent,
    StatefulKmpViewModelImpl<SearchState>(),
    SearchViewModel {

    private val searchService: SearchService by inject()
    private val urlEncode: UrlEncode by inject()
    private val appMetricaService: AppMetricaService by inject()

    private val _state = MutableStateFlow(SearchState())
    override val state: StateFlow<SearchState> = _state.asStateFlow()

    private val pager = Pager<ProductData>(PaginationType.SEARCH, searchService)

    override val statePaging: StateFlow<SearchState> =
        pager.state.map { pagingData ->
            val list = pagingData.data.map {
                ProductModel(it, state.value.searchString)
            }.toMutableList()
            SearchState(
                PagingData<ProductModel>(
                    loadingState = pagingData.loadingState,
                    isRefreshing = pagingData.isRefreshing,
                    isAppending = pagingData.isAppending,
                    data = list,
                    name = state.value.searchString
                )
            )
        }.stateIn(scope, SharingStarted.WhileSubscribed(), SearchState())

    override fun onViewShown() {
        super.onViewShown()
        if (statePaging.value.pagingData.data.isEmpty()){
            pager.onRefresh()
        }
        appMetricaService.sendEvent(
            LogEvent.OPEN_SCREEN, mapOf(
                LogEventParam.SCREEN_NAME to "Поиск",
                LogEventParam.SCREEN_CLASS to "SearchScreen",
            )
        )
    }

    override fun onViewHidden() {
        super.onViewHidden()
        pager.onViewHidden()
    }

    override fun searchChanged(search: String) {
        _state.update { it.copy(searchString = search) }
        pager.updateSearchText(state.value.searchString)
    }

    override fun onDeleteSearchClick() {
        searchChanged("")
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

    override fun selectCategory(category: CategoryModel) {
        _state.update { it.copy(selectCategory = category) }
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

    private fun setLoadingRefreshable(status: Boolean) {
        _state.update { it.copy(refreshable = status) }
    }
}
