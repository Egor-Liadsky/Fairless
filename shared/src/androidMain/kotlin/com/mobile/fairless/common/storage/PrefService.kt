package com.mobile.fairless.common.storage

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.mobile.fairless.features.welcome.dto.UserReceive
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual interface NativePrefService

actual class PrefServiceImpl : PrefService, KoinComponent {

    private val context: Application by inject()

    private val gson = Gson()
    private var preferences: SharedPreferences =
        context.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)

    override fun setUserInfo(user: UserReceive) {
        preferences.edit().putString(USER, gson.toJson(user)).apply()
    }

    override fun getUserInfo(): UserReceive? {
        Log.e("qweqweqweqweqwe", gson.fromJson(preferences.getString(USER, ""), UserReceive::class.java).toString())
        return gson.fromJson(preferences.getString(USER, ""), UserReceive::class.java)
    }
}
