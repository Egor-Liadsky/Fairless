package com.mobile.fairless.android.features.document.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.features.views.buttons.CommonButton
import com.mobile.fairless.android.features.views.buttons.CommonButtonParams
import com.mobile.fairless.android.features.views.buttons.ShapeButton
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
                        color = colors.black,
                        textDecoration = TextDecoration.LineThrough
                    ),
                    modifier = Modifier.padding(start = 15.dp)
                )
                Text(
                    text = "(-${100 - ((product.sale_price!! * 100) / product.sale_old_price!!)}%)",
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        color = colors.black,
                    ),
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = product.shop?.name ?: "",
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = colors.black
                ),
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (product.count_likes != null) {
                    IconButton(modifier = Modifier.padding(end = 3.dp), onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_like),
                            contentDescription = "ic_like",
                            modifier = Modifier
                                .size(20.dp)
                        )
                    }
                    Text(text = product.count_likes ?: "")
                }

                if (product.count_dislikes != null) {
                    IconButton(
                        modifier = Modifier.padding(start = 20.dp, end = 3.dp),
                        onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dislike),
                            contentDescription = "ic_dislike",
                            modifier = Modifier
                                .size(20.dp)
                        )
                    }
                    Text(text = product.count_dislikes ?: "")
                }
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            colors.grayDivider
        )

        if (product.users_permissions_user != null) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.avatarka),
                    contentDescription = "avatarka",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 10.dp)
                )
                Text(text = product.users_permissions_user!!.username)
            }
        }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            ShapeButton(title = "Комментарии", modifier = Modifier.padding(top = 10.dp)) {

            }

            CommonButton(
                commonButtonParams = CommonButtonParams(
                    title = "Перейти к скидке",
                    titleColor = colors.white,
                    background = colors.orangeMain
                ), modifier = Modifier.padding(top = 10.dp)
            ) {

            }
        }

        Text(
            text = product.description ?: "",
            style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = colors.black,
            ),
            modifier = Modifier.padding(top = 25.dp)
        )
    }
}
