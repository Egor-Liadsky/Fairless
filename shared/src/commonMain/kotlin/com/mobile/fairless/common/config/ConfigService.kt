package com.mobile.fairless.common.config

import com.mobile.fairless.BuildKonfig

expect interface NativeConfigService

interface ConfigService : NativeConfigService {

    fun getBaseUrl(): String = BuildKonfig.BASE_URL
    fun getAppMetricaApikey(): String = BuildKonfig.APPMETRICA_KEY
}

expect class ConfigServiceImpl() : ConfigService
