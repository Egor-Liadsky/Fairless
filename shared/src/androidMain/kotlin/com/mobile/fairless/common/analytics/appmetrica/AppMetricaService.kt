package com.mobile.fairless.common.analytics.appmetrica

import android.app.Application
import com.mobile.fairless.common.config.ConfigService
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual interface NativeAppMetricaService {
    fun init()
}

actual class AppMetricaServiceImpl : KoinComponent, AppMetricaService {
    private val configService: ConfigService by inject()
    private val context: Application by inject()

    companion object {
        val eventMap = mapOf<LogEvent, String>()
        val paramMap = mapOf<LogEventParam, String>()
    }

    override fun init() {
        val config =
            YandexMetricaConfig.newConfigBuilder(configService.getAppMetricaApikey()).build()
        YandexMetrica.activate(context, config)
        YandexMetrica.enableActivityAutoTracking(context)
    }

    override fun sendEvent(event: LogEvent, params: Map<LogEventParam, Any>) {
        YandexMetrica.reportEvent(eventMap[event] ?: event.str, params.mapKeys {
            paramMap[it.key] ?: it.key.str
        })
    }
}
