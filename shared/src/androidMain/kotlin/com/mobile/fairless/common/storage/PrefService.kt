package com.mobile.fairless.common.storage

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.mobile.fairless.features.welcome.models.UserReceive
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

    override fun getUserInfo(): UserReceive {
        val data = preferences.getString(USER, "")
        return if (data?.isEmpty() == true) {
            UserReceive()
        } else {
            gson.fromJson(preferences.getString(USER, ""), UserReceive::class.java)
        }
    }
}
