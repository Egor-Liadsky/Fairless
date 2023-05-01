package com.mobile.fairless.android.features.welcome.auth

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.features.welcome.auth.layouts.AuthLayout

@Composable
fun AuthScreen() {

    BackHandler {

    }

    Column(Modifier.fillMaxSize()) {
        AuthLayout()
    }
}
