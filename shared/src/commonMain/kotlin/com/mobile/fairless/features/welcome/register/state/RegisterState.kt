package com.mobile.fairless.features.welcome.register.state

data class RegisterState(
    val email: String? = null,
    val login: String? = null,
    val password: String? = null,
    val passwordRetry: String? = null,
)
