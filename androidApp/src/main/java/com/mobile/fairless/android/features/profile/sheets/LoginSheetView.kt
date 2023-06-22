package com.mobile.fairless.android.features.profile.sheets

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
fun LoginSheetView(sheetState: ModalBottomSheetState, state: State<ProfileState>) {
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Изменение логина", style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = colors.black
            )
        )

        MiniTextField(
            modifier = Modifier.padding(vertical = 20.dp),
            onValueChanged = {},
            placeholder = "Новый логин"
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
