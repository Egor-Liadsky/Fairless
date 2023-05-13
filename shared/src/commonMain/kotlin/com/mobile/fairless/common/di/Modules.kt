package com.mobile.fairless.common.di

import com.mobile.fairless.common.config.ConfigService
import com.mobile.fairless.common.config.ConfigServiceImpl
import com.mobile.fairless.common.storage.PrefService
import com.mobile.fairless.common.storage.PrefServiceImpl
import com.mobile.fairless.features.main.repository.MainRepository
import com.mobile.fairless.features.main.repository.MainRepositoryImpl
import com.mobile.fairless.features.main.service.MainService
import com.mobile.fairless.features.main.service.MainServiceImpl
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.mainNavigation.service.ErrorServiceImpl
import com.mobile.fairless.features.welcome.auth.repository.AuthRepository
import com.mobile.fairless.features.welcome.auth.repository.AuthRepositoryImpl
import com.mobile.fairless.features.welcome.auth.service.AuthService
import com.mobile.fairless.features.welcome.auth.service.AuthServiceImpl
import com.mobile.fairless.features.welcome.register.repository.RegisterRepository
import com.mobile.fairless.features.welcome.register.repository.RegisterRepositoryImpl
import com.mobile.fairless.features.welcome.register.service.RegisterService
import com.mobile.fairless.features.welcome.register.service.RegisterServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule(), platformModule())
    }

fun commonModule() = module {
    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }
    single {
        HttpClient(get()) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Ktor: $message")
                    }
                }
                level = LogLevel.ALL
            }
        }
    }

    // Services
    single<ErrorService> { ErrorServiceImpl() }
    single<AuthService> { AuthServiceImpl(get()) }
    single<RegisterService> { RegisterServiceImpl(get()) }
    single<ConfigService> { ConfigServiceImpl() }
    single<MainService> { MainServiceImpl(get()) }

    // Repositories
    single<AuthRepository> { AuthRepositoryImpl() }
    single<RegisterRepository> { RegisterRepositoryImpl() }
    single<MainRepository> { MainRepositoryImpl() }

    // Settings
    single<PrefService> { PrefServiceImpl() }
}
