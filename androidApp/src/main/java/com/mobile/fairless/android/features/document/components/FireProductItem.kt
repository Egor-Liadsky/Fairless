package com.mobile.fairless.android.features.document.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Indication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mobile.fairless.android.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.android.utils.Constants
import com.mobile.fairless.features.main.models.ProductData

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FireProductItem(product: ProductData, onClick: () -> Unit) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .width(180.dp),
        onClick = { onClick() },
        elevation = 2.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = product.stock_type?.name ?: "",
                        style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            color = colors.orangeMain
                        )
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_thunder),
                        contentDescription = "ic_thunder",
                        modifier = Modifier
                            .padding(start = 3.dp)
                            .size(10.dp),
                        tint = colors.orangeMain
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_like),
                        contentDescription = "ic_like",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(15.dp)
                    )
                    Text(
                        text = product.count_likes ?: "",
                        style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            color = colors.black
                        )
                    )
                }
            }

            AsyncImage(
                model = Constants.BASE_URL + product.image?.url,
                contentDescription = "product_image",
                modifier = Modifier
                    .width(120.dp)
                    .height(180.dp)
                    .padding(horizontal = 10.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = product.name ?: "",
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = colors.black
                ),
                modifier = Modifier.padding(bottom = if (product.sale_price == null) 5.dp else 0.dp)
            )

            if (product.sale_price != null) {
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${product.sale_price} ₽",
                        style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = colors.orangeMain
                        )
                    )
                    Text(
                        text = "${product.sale_old_price} ₽",
                        style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            color = colors.black,
                            textDecoration = TextDecoration.LineThrough
                        ),
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Text(
                        text = "(-${100 - ((product.sale_price!! * 100) / product.sale_old_price!!)}%)",
                        style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp,
                            color = colors.black,
                        ),
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
            if (product.promo_code != null) {
                Column(modifier = Modifier.fillMaxWidth().border(0.5.dp, colors.black, RoundedCornerShape(5.dp)).clip(RoundedCornerShape(5.dp))
                    .clickable{
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        clipboard.setPrimaryClip(ClipData.newPlainText("Промокод", product.promo_code))
                        Toast.makeText(context, "Скопировано", Toast.LENGTH_SHORT).show()
                    }) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = product.promo_code!!,
                            style = TextStyle(
                                fontFamily = fontQanelas,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 10.sp,
                                color = colors.black
                            )
                        )
                        Icon(painter = painterResource(id = R.drawable.ic_copy), contentDescription = "ic_copy",
                            modifier = Modifier.size(20.dp), tint = colors.black)
                    }
                }
            }
        }
    }
}

