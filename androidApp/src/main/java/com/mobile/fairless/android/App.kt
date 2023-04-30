package com.mobile.fairless.android

import android.app.Application
import com.mobile.fairless.android.di.androidModule
import com.mobile.fairless.common.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            allowOverride(true)
            modules(androidModule())
        }
    }
}
