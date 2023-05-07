package com.mobile.fairless.common.storage

import com.mobile.fairless.features.welcome.dto.UserReceive

const val USER = "USER"

expect interface NativePrefService

interface PrefService : NativePrefService {
    fun setUserInfo(user: UserReceive)
    fun getUserInfo(): UserReceive?
}

expect class PrefServiceImpl() : PrefService
