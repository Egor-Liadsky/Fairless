package com.mobile.fairless.features.welcome.auth.state

import com.mobile.fairless.features.welcome.dto.UserReceive

data class AuthState(
    val email: String? = null,
    val password: String? = null,

    val isLoading: Boolean = false,

    val user: UserReceive? = null
)
