package com.mobile.fairless.common.exception

data class SocketException(
    private val _message: String? = null
): Exception(_message)
