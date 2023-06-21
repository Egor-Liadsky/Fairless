package com.mobile.fairless.features.search.pagination

import com.mobile.fairless.common.errors.AppError
import com.mobile.fairless.common.state.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class SearchPager<T : Any>(
    private val source: SearchPagingDataSource<T>
) {
    private val mutableState: MutableStateFlow<SearchPagingData<T>> =
        MutableStateFlow(SearchPagingData())

    val state: StateFlow<SearchPagingData<T>>
        get() = mutableState

    private val mutex = Mutex()
    private val scope = CoroutineScope(Dispatchers.Default)
    private var job: Job? = null

    private var page: Int = 0
    private var appending: Boolean = false

    init {
        job = scope.launch {
            try {
                val data = getPage()
                mutableState.value.data.addAll(data)
                println("Asdjhasdjhagsdjh    ${mutableState.value.data}")
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
        val name = mutableState.value.name
        if (name != "") {
            mutex.withLock {
                try {
                    val response = source.getPage(page, name)
                    if (response.list.isNotEmpty())
                        repeat(30) { page++ }
                    return response.list
                } catch (ex: Exception) {
                    return emptyList()
                }
            }
        } else {
            return emptyList()
        }
    }

    fun updateName(name: String) {
        mutableState.update { it.copy(name = name) }
        job?.cancel()
        appending = false
        job = scope.launch {
            page = 0
            mutableState.value.data.clear()
            mutableState.update {
                it.copy(
                    loadingState = LoadingState.Loading,
                    isRefreshing = false
                )
            }
            try {
                val data = getPage()
                mutableState.value.data.addAll(data)
                if (data.isEmpty()){
                    delay(200)
                    mutableState.update { it.copy(loadingState = LoadingState.Empty) }
                } else {
                    mutableState.update { it.copy(loadingState = LoadingState.Success) }
                }
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

    fun onRefresh() {
        job?.cancel()
        appending = false
        job = scope.launch {
            page = 0
            mutableState.value.data.clear()
            mutableState.update {
                it.copy(
                    loadingState = LoadingState.Loading,
                    isRefreshing = true
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
                }//TODO
            } finally {
                mutableState.update { it.copy(isRefreshing = false) }
            }
        }
    }

    fun onViewHidden() {
        job?.cancel()
    }
}

interface SearchPaginatedResult<T : Any> {
    val page: Int?
    val list: List<T>
}

data class SearchPagingData<T : Any>(
    var loadingState: LoadingState = LoadingState.Loading,
    var isRefreshing: Boolean = false,
    var isAppending: Boolean = false,
    var name: String = "",

    val data: MutableList<T> = mutableListOf()
)

interface SearchPagingDataSource<T : Any> {
    suspend fun getPage(page: Int, name: String): SearchPaginatedResult<T>
}
