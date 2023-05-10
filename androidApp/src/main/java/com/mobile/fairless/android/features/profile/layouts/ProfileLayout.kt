package com.mobile.fairless.android.features.profile.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.profile.viewModel.ProfileViewModel

@Composable
fun ProfileLayout(viewModelWrapper: ViewModelWrapper<ProfileViewModel>) {

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
//            modifier = Modifier.size(200.dp)
//                .padding(top = 10.dp, bottom = 30.dp)
//        )
    }
}
