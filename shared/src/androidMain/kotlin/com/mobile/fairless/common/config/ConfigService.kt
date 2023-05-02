package com.mobile.fairless.common.config

import org.koin.core.component.KoinComponent

actual interface NativeConfigService

actual class ConfigServiceImpl : KoinComponent, ConfigService {

}
