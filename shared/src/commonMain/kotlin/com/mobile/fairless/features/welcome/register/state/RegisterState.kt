package com.mobile.fairless.features.welcome.register.state

import com.mobile.fairless.features.welcome.dto.City
import com.mobile.fairless.features.welcome.dto.UserReceive

data class RegisterState(
    val email: String? = null,
    val login: String? = null,
    val city: City? = null,
    val password: String? = null,
    val passwordRetry: String? = null,
    val search: String? = null,

    val cities: List<City>? = null,
    val stage: Int = 1,

    val isLoading: Boolean = false,
    val alertDialogOpen: Boolean = false,

    val user: UserReceive? = null
)
