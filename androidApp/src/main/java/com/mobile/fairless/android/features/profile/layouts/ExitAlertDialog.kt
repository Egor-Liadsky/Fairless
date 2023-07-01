package com.mobile.fairless.android.features.profile.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas

@Composable
fun ExitAlertDialog(
    isOpen: Boolean,
    closeAlertDialogClick: () -> Unit,
    exitClick: () -> Unit
) {
    var status by remember {
        mutableStateOf(isOpen)
    }

    if (isOpen) {
        Dialog(
            onDismissRequest = {
                status = false
                closeAlertDialogClick()
            }) {
            Column(
                Modifier
                    .background(colors.white, shape = RoundedCornerShape(5.dp))
                    .width(270.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(Modifier.padding(top = 24.dp, bottom = 20.dp, start = 24.dp, end = 24.dp)) {
                    Text(
                        text = "Вы уверены, что хотите\nвыйти из аккаунта?",
                        style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.W600,
                            fontSize = 16.sp,
                            color = colors.black,
                            textAlign = TextAlign.Center
                        ), modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        Modifier.fillMaxWidth().padding(top = 22.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box {
                            DefaultExitButton(title = "Нет") {
                                closeAlertDialogClick()
                            }
                        }
                        Box {
                            ExitGradientButton(title = "Да") {
                                exitClick()
                                closeAlertDialogClick()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DefaultExitButton(
    modifier: Modifier = Modifier,
    background: Color = colors.navBar,
    title: String,
    onClick: () -> Unit
) {
    Column {
        Button(
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(backgroundColor = background),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
            contentPadding = PaddingValues(),
        ) {
            Row(
                modifier = modifier
                    .padding(vertical = 12.dp, horizontal = 39.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = colors.black
                    ),
                )
            }
        }
    }
}

@Composable
fun ExitGradientButton(
    modifier: Modifier = Modifier,
    title: String,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {
    val orangeGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFF51B00),
            Color(0xFFFF8D00)
        )
    )
    Column {
        Button(
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
            contentPadding = PaddingValues(),
        ) {
            Row(
                modifier = modifier
                    .background(orangeGradient)
                    .padding(vertical = 12.dp, horizontal = 39.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (!isLoading) {
                    Text(
                        text = title,
                        style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = colors.white
                        ),
                    )
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(20.dp),
                        color = colors.white
                    )
                }
            }
        }
    }
}