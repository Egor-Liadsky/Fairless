package com.mobile.fairless.common.pagination

import com.mobile.fairless.common.errors.AppError
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.features.main.models.ProductStockType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


class Pager<T : Any>(
    val isMain: Boolean,
    private val source: PagingDataSourceMain<T>
) {
    private val mutableState: MutableStateFlow<PagingData<T>> = MutableStateFlow(PagingData())

    val state: StateFlow<PagingData<T>>
        get() = mutableState

    private val mutex = Mutex()
    private val scope = CoroutineScope(Dispatchers.Default)
    private var job: Job? = null

    private var page: Int = 1
    private var total: Int = 1
    private var appending: Boolean = false

    init {
        job = scope.launch {
            try {
                val data = getPage()
                mutableState.value.data.addAll(data)
                mutableState.update { it.copy(loadingState = if (data.isEmpty()) LoadingState.Empty else LoadingState.Success) }
            } catch (e: AppError) {
                mutableState.update {
                    it.copy(
                        loadingState = LoadingState.Error(
                            e.description ?: "We know"
                        )
                    )
                }
            }
        }
    }

    private suspend fun getPage(): List<T> {
        val category = mutableState.value.category
        val name = mutableState.value.name
        val type = mutableState.value.type


        if (isMain){
            total = source.getPage(page, category, type).total ?: 1 // Получение количества страниц

            mutex.withLock {
                if (page > total) {
                    return listOf()
                }
                val response = source.getPage(page, category, type)
                if (response.list.isNotEmpty())
                    page++
                return response.list
            }
        } else {
            println("asdkjahsd   " + name)
            if (name != ""){
                total = source.getPage(page, name, type).total ?: 1 // Получение количества страниц

                mutex.withLock {
                    if (page > total) {
                        return listOf()
                    }
                    val response = source.getPage(page, name, type)
                    if (response.list.isNotEmpty())
                        page++
                    return response.list
                }
            } else {
                return emptyList()
            }
        }
    }

    fun changeType(type: ProductStockType){
        mutableState.update { it.copy(type = type) }
        reloadData()
    }

    fun updateCategory(category: String){
        mutableState.update { it.copy(category = category) }
        reloadData()
    }

    fun updateSearchText(text: String) {
        mutableState.update { it.copy(name = text) }
        reloadData()
    }

    fun onRefresh() {
        reloadData(isRefreshing = true)
    }

    fun onAppend() {
        if (appending) return
        appending = true //TODO

        job = scope.launch {
            mutableState.update { it.copy(isAppending = true) }
            try {
                val data = getPage()
                mutableState.value.data.addAll(data)
            } catch (e: AppError) {
                //TODO mb add log event
            } finally {
                mutableState.update { it.copy(isAppending = false) }
                appending = false
            }
        }
    }

    private fun reloadData(isRefreshing: Boolean = false) {
        job?.cancel()
        appending = false
        job = scope.launch {
            page = 1
            mutableState.value.data.clear()
            mutableState.update {
                it.copy(
                    loadingState = LoadingState.Loading,
                    isRefreshing = isRefreshing
                )
            }
            try {
                val data = getPage()
                mutableState.value.data.addAll(data)
                mutableState.update { it.copy(loadingState = if (data.isEmpty()) LoadingState.Empty else LoadingState.Success) }
            } catch (e: AppError) {
                mutableState.update {
                    it.copy(
                        loadingState = LoadingState.Error(
                            e.description ?: "We know"
                        )
                    )
                }
            } finally {
                if (isRefreshing) mutableState.update { it.copy(isRefreshing = false) }
            }
        }
    }


    fun onViewHidden() {
        job?.cancel()
    }
}

interface PaginatedResult<T : Any> {
    val page: Int?
    val total: Int?
    val list: List<T>
}

data class PagingData<T : Any>(
    var loadingState: LoadingState = LoadingState.Loading,
    var isRefreshing: Boolean = false,
    var isAppending: Boolean = false,
    var category: String = "all",
    var name: String = "",
    var type: ProductStockType = ProductStockType.ALL,

    val data: MutableList<T> = mutableListOf()
)

interface PagingDataSourceMain<T : Any> {
    suspend fun getPage(page: Int, name: String, type: ProductStockType): PaginatedResult<T>
}
