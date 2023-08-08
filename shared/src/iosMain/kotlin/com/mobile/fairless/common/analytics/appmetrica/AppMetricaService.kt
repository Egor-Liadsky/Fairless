package com.mobile.fairless.common.analytics.appmetrica

import org.koin.core.component.KoinComponent

actual interface NativeAppMetricaService {
    fun init()
}

actual class AppMetricaServiceImpl : KoinComponent, AppMetricaService {

    override fun init() {
//        TODO ("Not implemented")
    }

    override fun sendEvent(event: LogEvent, params: Map<LogEventParam, Any>) {
//        TODO ("Not implemented")
    }
}
