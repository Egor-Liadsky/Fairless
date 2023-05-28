package com.mobile.fairless.android.features.document.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.document.components.FireProductItem
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.document.viewModel.DocumentViewModel

@Composable
fun FireProductsLayout(viewModelWrapper: ViewModelWrapper<DocumentViewModel>) {

    val state = viewModelWrapper.viewModel.state.collectAsState()

    // TODO найти другой способ реализации Grid

    if (state.value.fireProduct.isNotEmpty()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_fire),
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
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                elevation = ButtonDefaults.elevation(pressedElevation = 0.dp, defaultElevation = 0.dp),
                contentPadding = PaddingValues(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(text = "См.Все",
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = colors.orangeMain
                    ))
            }
        }
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FireProductItem(product = state.value.fireProduct[0]) {
                    viewModelWrapper.viewModel.onDocumentClick(state.value.fireProduct[0])
                }
                FireProductItem(product = state.value.fireProduct[1]) {
                    viewModelWrapper.viewModel.onDocumentClick(state.value.fireProduct[1])
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FireProductItem(product = state.value.fireProduct[2]) {
                    viewModelWrapper.viewModel.onDocumentClick(state.value.fireProduct[2])
                }
                FireProductItem(product = state.value.fireProduct[3]) {
                    viewModelWrapper.viewModel.onDocumentClick(state.value.fireProduct[3])
                }
            }
        }
    }
}
