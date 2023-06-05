package com.mobile.fairless.android.features.document.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.features.profile.components.DefaultButton
import com.mobile.fairless.android.features.views.buttons.GradientButton
import com.mobile.fairless.android.features.views.textFields.BigTextField
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.document.state.DocumentState
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SendCommentSheetView(
    sheetState: ModalBottomSheetState,
    state: State<DocumentState>,
    onClick: () -> Unit,
    onValueChanged: (String) -> Unit,
    errorService: ErrorService = get()
) {
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Divider(
            Modifier
                .width(30.dp)
                .clip(RoundedCornerShape(2.dp))
                .padding(top = 10.dp, bottom = 10.dp),
            color = colors.navBar,
            thickness = 3.dp
        )

        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Оставить комментарий", style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = colors.black
                )
            )

            BigTextField(
                modifier = Modifier
                    .height(285.dp)
                    .padding(top = 20.dp),
                onValueChanged = { onValueChanged(it) })

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DefaultButton(Modifier.fillMaxWidth(0.5f), title = "Отменить") {
                        scope.launch { sheetState.hide() }
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                    GradientButton(Modifier.fillMaxWidth(1f), title = "Отправить") {
                        focusManager.clearFocus()
                        if (state.value.commentText != ""){
                            onClick()
                            scope.launch { sheetState.hide() }
                        } else {
                            scope.launch { errorService.showError("Введите текст") }
                        }
                    }
                }
            }
        }
    }
}
