package com.mobile.fairless.android.features.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas

@Composable
fun ProfileCategoryButton(modifier: Modifier = Modifier, profileCategoryItem: ProfileCategoryItem) {
    Column(modifier.fillMaxWidth()) {
        Text(
            text = profileCategoryItem.title, style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = Color(0xFFA7ACAF)
            ), modifier = Modifier.padding(top = 16.dp)
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 7.dp)
                .background(colors.navBar, shape = RoundedCornerShape(5.dp))
                .clip(RoundedCornerShape(5.dp))
                .clickable(enabled = profileCategoryItem.isEnabled) {
                    profileCategoryItem.onClick()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (profileCategoryItem.isEnabled) Arrangement.SpaceBetween else Arrangement.Start
        ) {
            Text(
                text = profileCategoryItem.placeholder, style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = colors.black
                ), modifier = Modifier.padding(horizontal = 10.dp, vertical = 12.dp)
            )
            if (profileCategoryItem.isEnabled){
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_next), contentDescription = "ic_next",
                        modifier = Modifier.size(width = 5.dp, height = 11.dp),
                        tint = colors.black
                    )
                }
            }
        }
    }
}
