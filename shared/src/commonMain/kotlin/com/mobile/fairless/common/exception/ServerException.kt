package com.mobile.fairless.common.exception

data class ServerException(
    val errorCode: Int,
    val errorMessage: String
): Exception(errorMessage)
