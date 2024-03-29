package com.mobile.fairless.features.welcome.register.state

import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.features.welcome.models.City
import com.mobile.fairless.features.welcome.models.UserReceive

data class RegisterState(
    val email: String? = null,
    val login: String? = null,
    val city: City? = null,
    val password: String? = null,
    val passwordRetry: String? = null,
    val search: String = "",

    val cities: List<City>? = null,
    val stage: Int = 1,

    val isLoading: Boolean = false,
    val isLoadingCity: LoadingState = LoadingState.Loading,
    val alertDialogOpen: Boolean = false,

    val user: UserReceive? = null
)
