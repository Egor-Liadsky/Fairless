package com.mobile.fairless.common.di

import org.koin.core.module.Module
import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*


actual fun platformModule(): Module = module {
    single<HttpClientEngine> {
        Darwin.create { }
    }
//    single<UrlEncode> {
//
//    }
}
