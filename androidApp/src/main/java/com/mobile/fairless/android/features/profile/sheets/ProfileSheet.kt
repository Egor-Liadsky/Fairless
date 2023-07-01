package com.mobile.fairless.android.features.profile.sheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.profile.state.ProfileState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileSheet(
    sheetState: ModalBottomSheetState,
    state: State<ProfileState>,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxWidth()
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
        content()
    }
}
