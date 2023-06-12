package com.mobile.fairless.common.state

sealed class LoadingState {
    object Loading: LoadingState()
    object Success: LoadingState()
    object Empty: LoadingState()
    data class Error(val error: String): LoadingState()
}
