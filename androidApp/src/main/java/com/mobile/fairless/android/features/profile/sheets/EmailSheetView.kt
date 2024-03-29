package com.mobile.fairless.android.features.profile.sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.features.profile.components.DefaultButton
import com.mobile.fairless.android.features.views.buttons.GradientButton
import com.mobile.fairless.android.features.views.textFields.MiniTextField
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.profile.state.ProfileState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmailSheetView(sheetState: ModalBottomSheetState, state: State<ProfileState>) {
    val scope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxWidth()
            .background(colors.white), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Изменение E-mail", style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = colors.black
            )
        )

        Text(
            text = "Вы получите письмо со ссылкой для подтверждения нового адреса электронной почты.",
            style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = colors.black
            ),
            modifier = Modifier.padding(top = 20.dp)
        )

        MiniTextField(
            modifier = Modifier.padding(vertical = 20.dp),
            onValueChanged = {},
            placeholder = "Новый E-mail"
        )

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            GradientButton(title = "Сохранить") {
                scope.launch { sheetState.hide() }
            }
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            DefaultButton(title = "Отменить") {
                scope.launch { sheetState.hide() }
            }
        }
    }
}
