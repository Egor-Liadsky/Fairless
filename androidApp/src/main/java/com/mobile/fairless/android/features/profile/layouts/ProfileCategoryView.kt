package com.mobile.fairless.android.features.profile.layouts

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.features.profile.components.ProfileCategoryButton
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas

data class ProfileCategoryItem(
    val title: String,
    val placeholder: String,
    val isEnabled: Boolean = true,
    val onClick: () -> Unit
)

@Composable
fun ProfileCategoryView(
    modifier: Modifier = Modifier,
    title: String,
    profileCategoryItem: List<ProfileCategoryItem>
) {
    Column(
        modifier
            .padding(start = 16.dp, end = 16.dp)
            .border(1.dp, colors.navBar, RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp))
    ) {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
            Text(
                text = title, style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = colors.black
                ), modifier = Modifier.padding(bottom = 4.dp)
            )

            profileCategoryItem.forEach { item ->
                ProfileCategoryButton(
                    profileCategoryItem = item
                )
            }
        }
    }
}
