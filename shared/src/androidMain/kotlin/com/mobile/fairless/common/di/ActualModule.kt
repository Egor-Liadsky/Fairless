package com.mobile.fairless.common.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<HttpClientEngine> {
        Android.create {  }
    }
}
