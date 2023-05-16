package com.mobile.fairless.common.utils

import net.thauvin.erik.urlencoder.UrlEncoder

actual class UrlEncodeImpl: UrlEncode {
    override fun encodeToUrl(data: String): String = UrlEncoder.encode(data)
    override fun decodeToUrl(data: String): String = UrlEncoder.decode(data)
}
