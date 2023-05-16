package com.mobile.fairless.android.features.document.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.main.models.ProductData

@Composable
fun DocumentLayout(product: ProductData) {

    Column(modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp)) {
        Text(
            text = product.name ?: "", style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        )

        if (product.sale_price != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${product.sale_price} ₽",
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = colors.orangeMain
                    )
                )
                Text(
                    text = "${product.sale_old_price} ₽",
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        color = colors.black
                    ),
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
        }

        if (product.shop != null)
            Text(text = product.shop?.name ?: "")

        Text(text = product.description ?: "")
    }
}
