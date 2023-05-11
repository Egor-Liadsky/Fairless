package com.mobile.fairless.android.features.profileEdit.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.features.views.buttons.CommonButton
import com.mobile.fairless.android.features.views.buttons.CommonButtonParams
import com.mobile.fairless.android.features.views.textFields.CommonTextField
import com.mobile.fairless.android.features.views.textFields.CommonTextFieldParams
import com.mobile.fairless.android.features.welcome.register.components.SelectCityAlertDialog

@Composable
fun ProfileEditLayout() {
    Column(Modifier.fillMaxSize()) {
        Column(Modifier.padding(top = 40.dp)) {
            CommonTextField(
                commonTextFieldParams = CommonTextFieldParams(placeholder = "Ваш логин"),
                onValueChanged = { })
            CommonTextField(
                modifier = Modifier.padding(top = 20.dp),
                commonTextFieldParams = CommonTextFieldParams(placeholder = "Ваш E-mail"),
                onValueChanged = { })
            CommonTextField(
                modifier = Modifier.padding(top = 20.dp),
                commonTextFieldParams = CommonTextFieldParams(placeholder = "Ваш пароль"),
                onValueChanged = { })
            CommonTextField(
                modifier = Modifier.padding(top = 20.dp),
                commonTextFieldParams = CommonTextFieldParams(placeholder = "Подтвердите пароль"),
                onValueChanged = { })
        }
    }
}
