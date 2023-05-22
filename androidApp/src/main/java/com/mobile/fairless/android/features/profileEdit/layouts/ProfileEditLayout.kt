package com.mobile.fairless.android.features.profileEdit.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.features.views.textFields.CommonTextField

@Composable
fun ProfileEditLayout() {
    Column(Modifier.fillMaxSize()) {
        Column(Modifier.padding(top = 40.dp)) {
            CommonTextField(
                placeholder = "Ваш логин",
                onValueChanged = { })
            CommonTextField(
                modifier = Modifier.padding(top = 20.dp),
                placeholder = "Ваш E-mail",
                onValueChanged = { })
            CommonTextField(
                modifier = Modifier.padding(top = 20.dp),
                placeholder = "Ваш пароль",
                onValueChanged = { })
            CommonTextField(
                modifier = Modifier.padding(top = 20.dp),
                placeholder = "Подтвердите пароль",
                onValueChanged = { })
        }
    }
}
