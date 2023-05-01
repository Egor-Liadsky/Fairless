package com.mobile.fairless.features.welcome.auth.state

import com.mobile.fairless.features.welcome.auth.dto.UserReceive

data class AuthState(
    val email: String? = null,
    val password: String? = null,

    val user: UserReceive? = null
)
