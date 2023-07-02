package com.mobile.fairless.common.analytics.appmetrica

expect interface NativeAppMetricaService

interface AppMetricaService : NativeAppMetricaService {
    fun sendEvent(event: String, params: Map<String, Any>)
}

expect class AppMetricaServiceImpl() : AppMetricaService
