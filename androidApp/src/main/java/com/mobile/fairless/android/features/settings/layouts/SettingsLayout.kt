package com.mobile.fairless.android.features.settings.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.mobile.fairless.android.features.views.buttons.ShapeButton
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas

@Composable
fun SettingsLayout() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                colors.backgroundWelcome,
                shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
            )
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.avatarka),
//            contentDescription = "avatarka",
//            modifier = Modifier
//                .size(170.dp)
//                .padding(top = 10.dp)
//        )

        Text(
            text = "Редактировать фото", style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.Normal,
                color = colors.orangeMain,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(top = 20.dp, bottom = 30.dp)
        )
    }
    
//    Column(modifier = Modifier.padding(top = 40.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//        ShapeButton(title = "Личные данные", description = "редактировать личные данные") {
//
//        }
//        ShapeButton(title = "Уведомления", modifier = Modifier.padding(top = 20.dp)) {
//
//        }
//    }
}
