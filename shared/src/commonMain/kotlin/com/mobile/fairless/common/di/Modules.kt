package com.mobile.fairless.common.di

import com.mobile.fairless.common.analytics.appmetrica.AppMetricaService
import com.mobile.fairless.common.analytics.appmetrica.AppMetricaServiceImpl
import com.mobile.fairless.common.config.ConfigService
import com.mobile.fairless.common.config.ConfigServiceImpl
import com.mobile.fairless.common.storage.PrefService
import com.mobile.fairless.common.storage.PrefServiceImpl
import com.mobile.fairless.features.document.repository.DocumentRepository
import com.mobile.fairless.features.document.repository.DocumentRepositoryImpl
import com.mobile.fairless.features.document.service.DocumentService
import com.mobile.fairless.features.document.service.DocumentServiceImpl
import com.mobile.fairless.features.main.repository.MainRepository
import com.mobile.fairless.features.main.repository.MainRepositoryImpl
import com.mobile.fairless.features.main.service.MainService
import com.mobile.fairless.features.main.service.MainServiceImpl
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.mainNavigation.service.ErrorServiceImpl
import com.mobile.fairless.features.profile.repository.ProfileRepository
import com.mobile.fairless.features.profile.repository.ProfileRepositoryImpl
import com.mobile.fairless.features.profile.service.ProfileService
import com.mobile.fairless.features.profile.service.ProfileServiceImpl
import com.mobile.fairless.features.search.repository.SearchRepository
import com.mobile.fairless.features.search.repository.SearchRepositoryImpl
import com.mobile.fairless.features.search.service.SearchService
import com.mobile.fairless.features.search.service.SearchServiceImpl
import com.mobile.fairless.features.shop.repository.ShopRepository
import com.mobile.fairless.features.shop.repository.ShopRepositoryImpl
import com.mobile.fairless.features.shop.service.ShopService
import com.mobile.fairless.features.shop.service.ShopServiceImpl
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


// called by iOS
fun initKoin() = initKoin {}


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

    // Analytics
    single<AppMetricaService> { AppMetricaServiceImpl() }

    // Services
    single<ErrorService> { ErrorServiceImpl() }
    single<AuthService> { AuthServiceImpl(get()) }
    single<RegisterService> { RegisterServiceImpl(get()) }
    single<ConfigService> { ConfigServiceImpl() }
    single<MainService> { MainServiceImpl(get()) }
    single<DocumentService> { DocumentServiceImpl(get()) }
    single<SearchService> { SearchServiceImpl(get()) }
    single<ProfileService> { ProfileServiceImpl(get()) }
    single<ShopService> {ShopServiceImpl()}

    // Repositories
    single<AuthRepository> { AuthRepositoryImpl() }
    single<RegisterRepository> { RegisterRepositoryImpl() }
    single<MainRepository> { MainRepositoryImpl() }
    single<DocumentRepository> { DocumentRepositoryImpl() }
    single<SearchRepository> { SearchRepositoryImpl() }
    single<ProfileRepository> { ProfileRepositoryImpl() }
    single<ShopRepository> { ShopRepositoryImpl() }

    // Settings
    single<PrefService> { PrefServiceImpl() }
}
