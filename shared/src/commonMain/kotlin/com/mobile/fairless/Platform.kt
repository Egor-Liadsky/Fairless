package com.mobile.fairless

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform