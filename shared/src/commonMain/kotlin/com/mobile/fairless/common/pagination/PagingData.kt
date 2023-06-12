package com.mobile.fairless.common.pagination

import com.mobile.fairless.common.errors.AppError
import com.mobile.fairless.common.state.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 *
 */
class Pager<T : Any>(
    private val source: PagingDataSource<T>
) {
    private val mutableState: MutableStateFlow<PagingData<T>> = MutableStateFlow(PagingData())

    val state: StateFlow<PagingData<T>>
        get() = mutableState

    private val mutex = Mutex()
    private val scope = CoroutineScope(Dispatchers.Default)
    private var job: Job? = null

    private var page: Int = 1
    private var total: Int = 100
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
                }//TODO
            }
        }
    }

    private suspend fun getPage(): List<T> {
        println("asdasdasdasdas   $page")

        mutex.withLock {
            if (page > total) return listOf()
            val response = source.getPage(page)
            if (response.list.isNotEmpty())
                page++
            return response.list
        }
    }

    fun onAppend() {
        if (appending) return
        appending = true
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

interface PaginatedResult<T : Any> {
    val page: Int?
    val total: Int?
    val list: List<T>
    val count: Int
}

data class PagingData<T : Any>(
    var loadingState: LoadingState = LoadingState.Loading,
    var isRefreshing: Boolean = false,
    var isAppending: Boolean = false,

    val data: MutableList<T> = mutableListOf()
)

interface PagingDataSource<T : Any> {
    suspend fun getPage(page: Int): PaginatedResult<T>
}

