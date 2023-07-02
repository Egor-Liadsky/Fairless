package com.mobile.fairless.common.errors

data class AppError(
    val code: Code = Code.INTERNAL_ERROR,
    val description: String? = null
) : Error(description)

enum class Code {
    SERVER_ERROR,
    INTERNAL_ERROR
}
