package com.mobile.fairless.android.features.views.topBars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.features.views.buttons.SquareButton
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas

@Composable
fun CommonTopBar(
    title: String,
    description: String? = null,
    isBack: Boolean? = false,
    isEdit: Boolean? = false,
    isSave: Boolean? = false,
    editClick: () -> Unit? = {},
    backClick: () -> Unit? = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.backgroundWelcome)
            .padding(20.dp),
        horizontalArrangement = if (isEdit == true || isSave == true) Arrangement.SpaceBetween else Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isBack == true) {
            SquareButton(icon = painterResource(id = R.drawable.ic_back_button), iconSize = 15.dp) {
                backClick()
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.Bold,
                    fontSize =  25.sp,
                    color = colors.black,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(start = if (isEdit == false && isBack == true) 20.dp else 0.dp)
            )
            if (description != null) {
                Text(
                    text = description,
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = colors.black,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }

        if (isEdit == true) {
            SquareButton(icon = painterResource(id = R.drawable.ic_edit), iconSize = 30.dp) {
                editClick()
            }
        }

        if (isSave == true){
            SquareButton(icon = painterResource(id = R.drawable.ic_success), iconSize = 30.dp) {
                editClick()
            }
        }
    }
}
