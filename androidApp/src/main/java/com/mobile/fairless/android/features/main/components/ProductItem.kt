package com.mobile.fairless.android.features.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mobile.fairless.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.android.utils.Constants
import com.mobile.fairless.features.main.models.ProductData

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductItem(modifier: Modifier = Modifier, product: ProductData, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .padding(top = 10.dp)
            .border(0.5.dp, Color(0xFFE1E1E1), RoundedCornerShape(10.dp)),
        onClick = { onClick() },
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
                    .width(90.dp)
                    .height(120.dp)
                    .padding(horizontal = 10.dp, vertical = 20.dp)
            )

            Column(
                modifier = Modifier.padding(
                    start = 7.dp,
                    top = 20.dp,
                    bottom = 10.dp,
                    end = 15.dp
                )
            ) {
                Text(
                    text = product.name ?: "",
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = colors.black
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    if ((product.sale_price != null) && (product.sale_old_price != null)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${product.sale_price} ₽",
                                style = TextStyle(
                                    fontFamily = fontQanelas,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp,
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
                                modifier = Modifier.padding(start = 7.dp)
                            )
                            Text(
                                text = "(-${100 - ((product.sale_price!! * 100) / product.sale_old_price!!)}%)",
                                style = TextStyle(
                                    fontFamily = fontQanelas,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 10.sp,
                                    color = colors.black,
                                ),
                                modifier = Modifier.padding(start = 7.dp)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = product.shop?.name ?: "",
                        style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = colors.black
                        ),
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (product.count_likes != null) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_like),
                                contentDescription = "ic_like",
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(width = 14.dp, height = 11.dp),
                                tint = colors.black
                            )
                            Text(
                                text = product.count_likes ?: "",
                                style = TextStyle(
                                    fontFamily = fontQanelas,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 10.sp,
                                    color = colors.black
                                ),
                            )
                        }

                        if (product.count_dislikes != null) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_dislike),
                                contentDescription = "ic_dislike",
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 8.dp)
                                    .size(width = 14.dp, height = 11.dp),
                                tint = colors.black
                            )
                            Text(
                                text = product.count_dislikes ?: "",
                                style = TextStyle(
                                    fontFamily = fontQanelas,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 10.sp,
                                    color = colors.black
                                ),
                            )
                        }
                    }
                }


                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                        .height(0.35.dp),
                    colors.grayDivider
                )

                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = if (product.users_permissions_user != null) Arrangement.SpaceBetween else Arrangement.End
                ) {
                    if (product.users_permissions_user != null) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.avatarka),
                                contentDescription = "avatarka",
                                modifier = Modifier
                                    .size(16.dp)
                                    .clip(RoundedCornerShape(2.dp))
                            )
                            Text(
                                text = product.users_permissions_user!!.username ?: "",
                                style = TextStyle(
                                    fontFamily = fontQanelas,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 12.sp,
                                    color = colors.black
                                ), modifier = Modifier.padding(start = 6.dp)
                            )
                        }
                    }

                    RatingView(
                        likes = product.count_likes?.toInt() ?: 0,
                        dislikes = product.count_dislikes?.toInt() ?: 0
                    )
                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, bottom = 10.dp)
                        .height(0.35.dp),
                    colors.grayDivider
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_comment),
                            contentDescription = "ic_comment",
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(14.dp),
                            tint = colors.black
                        )
                        Text(
                            text = product.count_comments.toString(),
                            style = TextStyle(
                                fontFamily = fontQanelas,
                                fontWeight = FontWeight.Normal,
                                fontSize = 10.sp,
                                color = colors.black
                            ),
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_views),
                            contentDescription = "ic_views",
                            modifier = Modifier
                                .padding(start = 10.dp, end = 8.dp)
                                .size(width = 13.dp, height = 8.dp),
                            tint = colors.black
                        )
                        Text(
                            text = product.count_views ?: "",
                            style = TextStyle(
                                fontFamily = fontQanelas,
                                fontWeight = FontWeight.Normal,
                                fontSize = 10.sp,
                                color = colors.black
                            ),
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = product.stock_type?.name ?: "", style = TextStyle(
                                fontFamily = fontQanelas,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                color = colors.orangeMain
                            ), modifier = Modifier.padding(end = 2.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_thunder),
                            contentDescription = "ic_thunder",
                            modifier = Modifier.size(width = 8.dp, height = 11.dp),
                            tint = colors.orangeMain
                        )
                    }
                }
            }
        }
    }
}
