package com.mobile.fairless.common.analytics.appmetrica

expect interface NativeAppMetricaService

interface AppMetricaService : NativeAppMetricaService {
    fun sendEvent(event: LogEvent, params: Map<LogEventParam, Any>)
}

expect class AppMetricaServiceImpl() : AppMetricaService

enum class LogEvent(val str: String) {
    OPEN_SCREEN("screen_view"),
}

enum class LogEventParam(val str: String) {
    SCREEN_NAME("screen_name"),
    SCREEN_CLASS("screen_class"),
    CATEGORY_CODE("category_code"),
    PRODUCT_TYPE("product_type"),
    PRODUCT_ID("item_id"),
    SHOP_CODE("shop_code"),
    ERROR_MESSAGE("error_message")
}