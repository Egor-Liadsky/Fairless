package com.mobile.fairless.android.features.menu.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.menu.state.MenuState

@Composable
fun ProfileView(state: MenuState, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp)
            .border(1.dp, colors.navBar, RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp))
            .clickable(){
                onClick()
            }
    ) {
        if (state.user != null) {
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (state.user!!.user != null){
                        Row (verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.avatarka),
                                contentDescription = "ic_avatarka",
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                            )
                            Column(Modifier.padding(start = 16.dp)) {
                                Text(
                                    text = state.user!!.user?.username.toString(), style = TextStyle(
                                        fontSize = 20.sp,
                                        fontFamily = fontQanelas,
                                        fontWeight = FontWeight.Bold,
                                        color = colors.black
                                    )
                                )
                                Text(
                                    text = "На сервисе с " + state.user!!.user?.dateTime.toString(),
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = fontQanelas,
                                        fontWeight = FontWeight.SemiBold,
                                        color = colors.black
                                    )
                                )
                            }
                        }
                    } else {
                        Text(
                            text = "Авторизоваться", style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = fontQanelas,
                                fontWeight = FontWeight.Bold,
                                color = colors.black
                            )
                        )
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.ic_next),
                        contentDescription = "ic_next",
                        Modifier.size(width = 6.dp, height = 12.dp),
                        tint = colors.black
                    )
                }
            }
        }
    }
}
