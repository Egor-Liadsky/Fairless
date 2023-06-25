package com.mobile.fairless.common.errors

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val statusCode: Int,
    val error: String,
    override val message: String
) : Error(message)

enum class ApiErrorType {
    Security,
    InvalidCast,
    Data,
    General;

    fun toCode(): Code {
        return when (this) {
            Security -> Code.SERVER_ERROR
            InvalidCast -> Code.SERVER_ERROR
            Data -> Code.SERVER_ERROR
            General -> Code.SERVER_ERROR
        }
    }
}
