package com.mobile.fairless.common.di

import com.mobile.fairless.common.utils.UrlEncode
import com.mobile.fairless.common.utils.UrlEncodeImpl
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import net.thauvin.erik.urlencoder.UrlEncoder
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<HttpClientEngine> {
        Android.create { }
    }
    single<UrlEncode> { UrlEncodeImpl() }
}
