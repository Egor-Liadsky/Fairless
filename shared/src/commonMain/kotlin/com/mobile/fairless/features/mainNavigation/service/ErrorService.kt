package com.mobile.fairless.features.mainNavigation.service

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

interface ErrorService{
    val state: SharedFlow<String>
    suspend fun showError(message: String)
}
class ErrorServiceImpl : ErrorService {
    private val _errorState = MutableSharedFlow<String>()
    override val state = _errorState.asSharedFlow()

    override suspend fun showError(message: String) {
        println("TAGTAG $message")
        _errorState.emit(message)
    }
}
