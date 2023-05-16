package com.mobile.fairless.common.utils

interface UrlEncode {
    fun encodeToUrl(data: String): String
    fun decodeToUrl(data: String): String
}

expect class UrlEncodeImpl(): UrlEncode
