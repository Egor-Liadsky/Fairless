package com.mobile.fairless.common.di

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*
import org.koin.core.module.Module
import org.koin.dsl.module
import com.mobile.fairless.common.utils.UrlEncode
import com.mobile.fairless.common.utils.UrlEncodeImpl

actual fun platformModule(): Module = module {
    single<HttpClientEngine> {
        Darwin.create { }
    }
    single<UrlEncode> { UrlEncodeImpl() }
}
