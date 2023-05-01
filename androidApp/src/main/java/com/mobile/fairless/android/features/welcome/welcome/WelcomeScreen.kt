package com.mobile.fairless.android.features.welcome.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.features.welcome.welcome.layouts.WelcomeLayout

@Composable
fun WelcomeScreen() {
    Column(Modifier.fillMaxSize()) {
        WelcomeLayout()
    }
}
