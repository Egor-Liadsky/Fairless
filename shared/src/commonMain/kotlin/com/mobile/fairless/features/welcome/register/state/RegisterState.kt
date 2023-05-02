package com.mobile.fairless.features.welcome.register.state

import com.mobile.fairless.features.welcome.dto.UserReceive

data class RegisterState(
    val email: String? = null,
    val login: String? = null,
    val password: String? = null,
    val passwordRetry: String? = null,

    val isLoading: Boolean = false,

    val user: UserReceive? = null
)
