package com.mobile.fairless.android.features.document.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.document.components.FireButton
import com.mobile.fairless.android.features.document.components.FireProductItem
import com.mobile.fairless.android.features.main.components.ProductItem
import com.mobile.fairless.android.features.views.layouts.EmptyLayout
import com.mobile.fairless.android.features.views.layouts.ErrorLayout
import com.mobile.fairless.android.features.views.layouts.LoadingLayout
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.features.document.state.DocumentState
import com.mobile.fairless.features.document.viewModel.DocumentViewModel
import com.mobile.fairless.features.main.models.DateFilter

@Composable
fun FireProductsLayout(viewModelWrapper: StatefulViewModelWrapper<DocumentViewModel, DocumentState>) {

    val state = viewModelWrapper.state

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_shape_fire),
            contentDescription = "ic_fire",
            tint = colors.orangeMain,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 10.dp)
        )
        Text(
            text = "Огненные предложения",
            style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = colors.black
            )
        )
    }

    when (state.value.loadingStateFire) {

        LoadingState.Loading -> {
            LoadingLayout()
        }

        LoadingState.Success -> {
            FireButton(Modifier.padding(horizontal = 16.dp, vertical = 10.dp), state,
                periodOpen = state.value.periodMenuOpen,
                periodClick = { viewModelWrapper.viewModel.periodClick() }
            ) {
                viewModelWrapper.viewModel.selectFirePeriod(it)
            }

            Column(Modifier.padding(bottom = 16.dp)) {
                state.value.fireProduct.forEach { product ->
                    Column(Modifier.padding(horizontal = 16.dp)) {
                        ProductItem(product = product) {
                            viewModelWrapper.viewModel.onDocumentClick(product.name ?: "")
                        }
                    }
                }
            }
        }

        is LoadingState.Error -> {
            ErrorLayout {
                viewModelWrapper.viewModel.getFireProducts(DateFilter.TODAY)
            }
        }

        LoadingState.Empty -> {
            EmptyLayout()
        }

        else -> {}
    }
}
