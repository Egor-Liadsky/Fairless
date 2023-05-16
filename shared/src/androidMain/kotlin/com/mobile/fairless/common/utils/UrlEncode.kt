package com.mobile.fairless.common.utils

import java.net.URLDecoder
import java.net.URLEncoder

actual class UrlEncodeImpl: UrlEncode {
    override fun encodeToUrl(data: String): String = URLEncoder.encode(data, "UTF-8")
    override fun decodeToUrl(data: String): String = URLDecoder.decode(data, "UTF-8")
}
