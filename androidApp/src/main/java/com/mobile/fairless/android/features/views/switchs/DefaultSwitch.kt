package com.mobile.fairless.android.features.views.switchs

import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.theme.colors
import org.koin.androidx.compose.get

@Composable
fun DefaultSwitch(
    checked: Boolean,
    modifier: Modifier = Modifier,
    onClick: (Boolean) -> Unit = {},
) {
    val checkedTrackColor = colors.orangeMain.copy(0.6f)
    val uncheckedTrackColor = colors.backgroundWelcome.copy(0.6f)
    val checkedThumb = colors.orangeMain
    val uncheckedThumb = colors.backgroundWelcome

    Switch(
        checked = checked, onCheckedChange = {
            onClick(it)
        },
        modifier = modifier,
        colors = SwitchDefaults.colors(
            checkedTrackColor = checkedTrackColor,
            uncheckedTrackColor = uncheckedTrackColor,
            checkedThumbColor = checkedThumb,
            uncheckedThumbColor = uncheckedThumb,
        )
    )
}
