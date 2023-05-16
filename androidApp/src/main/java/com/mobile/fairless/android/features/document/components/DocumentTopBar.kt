package com.mobile.fairless.android.features.document.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mobile.fairless.android.R
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.views.buttons.SquareButton
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.utils.Constants
import com.mobile.fairless.features.document.viewModel.DocumentViewModel
import com.mobile.fairless.features.main.models.ProductData

@Composable
fun DocumentTopBar(product: ProductData, viewModelWrapper: ViewModelWrapper<DocumentViewModel>) {

    Row(
        modifier = Modifier
            .background(
                colors.white,
                shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
            )
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top
    ) {
        SquareButton(
            icon = painterResource(id = R.drawable.ic_back_button),
            backgroundColor = colors.backgroundWelcome
        ) {
            viewModelWrapper.viewModel.onBackButtonClick()
        }

        AsyncImage(
            model = Constants.BASE_URL + product.image?.url,
            contentDescription = "ic_image",
            modifier = Modifier.size(200.dp)
        )

        SquareButton(
            icon = painterResource(id = R.drawable.ic_link_share),
            backgroundColor = colors.backgroundWelcome,
            iconColor = colors.orangeMain
        ) {
            viewModelWrapper.viewModel.onBackButtonClick()
        }
    }
}
