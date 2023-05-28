package com.mobile.fairless.common.utils

import android.util.Log
import java.net.URLDecoder
import java.net.URLEncoder

actual class UrlEncodeImpl: UrlEncode {
    override fun encodeToUrl(data: String): String {
        return URLEncoder.encode(data, "UTF-8")
    }
    override fun decodeToUrl(data: String): String {
        val content = data.replace(Regex("%(?![0-9a-fA-F]{2})"),"%25")
        return URLDecoder.decode(content, "UTF-8")
    }
}
