package com.mobile.fairless.common.di

import com.mobile.fairless.common.config.ConfigService
import com.mobile.fairless.common.config.ConfigServiceImpl
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.mainNavigation.service.ErrorServiceImpl
import com.mobile.fairless.features.welcome.auth.repository.AuthRepository
import com.mobile.fairless.features.welcome.auth.repository.AuthRepositoryImpl
import com.mobile.fairless.features.welcome.auth.service.AuthService
import com.mobile.fairless.features.welcome.auth.service.AuthServiceImpl
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
    single<ConfigService> { ConfigServiceImpl() }

    // Repositories
    single<AuthRepository> { AuthRepositoryImpl() }
}
