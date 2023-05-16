package com.mobile.fairless.android.features.main.components

import androidx.compose.foundation.Image
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
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
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
import coil.compose.AsyncImage
import com.mobile.fairless.android.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.android.utils.Constants
import com.mobile.fairless.features.main.models.ProductData

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductItem(product: ProductData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .width(350.dp),
        onClick = { onClick() },
        elevation = 2.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = Constants.BASE_URL + product.image?.url,
                contentDescription = "product_image",
                modifier = Modifier
                    .width(120.dp)
                    .height(180.dp)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            )

            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = product.name ?: "",
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = colors.black
                    )
                )
                if (product.sale_price != null) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
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
                            fontSize = 13.sp,
                            color = colors.black
                        ),
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (product.count_likes != null){
                            Icon(
                                painter = painterResource(id = R.drawable.ic_like),
                                contentDescription = "ic_like",
                                modifier = Modifier
                                    .padding(end = 10.dp)
                                    .size(15.dp)
                            )
                            Text(text = product.count_likes ?: "")
                        }

                        if (product.count_dislikes != null) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_dislike),
                                contentDescription = "ic_dislike",
                                modifier = Modifier
                                    .padding(start = 20.dp, end = 10.dp)
                                    .size(15.dp)
                            )
                            Text(text = product.count_dislikes ?: "")
                        }
                    }
                }

                if (product.users_permissions_user != null) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        colors.grayDivider
                    )

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

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    colors.grayDivider
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_comment),
                        contentDescription = "ic_comment",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(15.dp)
                    )
                    Text(
                        text = product.count_comments.toString(),
                        style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = colors.black
                        ),
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_views),
                        contentDescription = "ic_views",
                        modifier = Modifier
                            .padding(start = 20.dp, end = 10.dp)
                            .size(15.dp)
                    )
                    Text(
                        text = product.count_views ?: "",
                        style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = colors.black
                        ),
                    )
                }
            }
        }
    }
}
