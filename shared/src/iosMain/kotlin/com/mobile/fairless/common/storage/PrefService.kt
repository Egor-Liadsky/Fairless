package com.mobile.fairless.common.storage

import com.mobile.fairless.features.welcome.dto.UserReceive
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual interface NativePrefService

actual class PrefServiceImpl : PrefService, KoinComponent {

    private val context: Application by inject()
//
//    private val gson = Gson()
//    private var preferences: SharedPreferences =
//        context.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)

    override fun setUserInfo(user: UserReceive) {
        TODO("Not yet implemented")
    }

    override fun getUserInfo(): UserReceive? {
        TODO("Not yet implemented")
    }
}
