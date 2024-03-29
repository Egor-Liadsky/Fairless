package com.mobile.fairless.android.features.document.layouts

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.R
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.main.components.RatingView
import com.mobile.fairless.android.features.profile.components.DefaultButton
import com.mobile.fairless.android.features.views.buttons.GradientButton
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.document.state.DocumentState
import com.mobile.fairless.features.document.viewModel.DocumentViewModel
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.models.Shop
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DocumentLayout(
    product: ProductData,
    sheetStateComment: ModalBottomSheetState,
    sheetStateShop: ModalBottomSheetState,
    viewModelWrapper: StatefulViewModelWrapper<DocumentViewModel, DocumentState>
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val state = viewModelWrapper.viewModel.state

    val orangeGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFF51B00),
            Color(0xFFFF8D00)
        )
    )
    val inputFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat = SimpleDateFormat("dd.MM.yyyy")
    val date = inputFormat.parse(product.date_end ?: "2006-09-08")
    val formattedDateEnd =
        outputFormat.format(date ?: "2006.09.08") // парсит число по которое будет идти скидка

    Column(modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp)) {
        if (product.date_end != null) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Опубликовано: ${product.dateTime}",
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = colors.black
                    ), modifier = Modifier.padding(end = 16.dp)
                )

                Row(
                    modifier = Modifier
                        .background(orangeGradient, shape = RoundedCornerShape(20.dp))
                ) {
                    Text(
                        text = "по $formattedDateEnd",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            color = colors.white,
                            textAlign = TextAlign.Center
                        ), modifier = Modifier.padding(vertical = 6.dp, horizontal = 16.dp)
                    )
                }
            }
        }

        Text(
            text = product.name ?: "",
            style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = colors.black
            ),
            modifier = Modifier.padding(top = 15.dp),
        )

        if (product.promo_code != null) {
            Column(modifier = Modifier
                .padding(top = 20.dp)
                .border(0.5.dp, colors.black, RoundedCornerShape(5.dp))
                .clip(RoundedCornerShape(5.dp))
                .clickable {
                    val clipboard =
                        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    clipboard.setPrimaryClip(
                        ClipData.newPlainText(
                            "Промокод",
                            product.promo_code
                        )
                    )
                    Toast
                        .makeText(context, "Скопировано", Toast.LENGTH_SHORT)
                        .show()
                }) {
                Row(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = product.promo_code!!,
                        style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = colors.black
                        ),
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_copy),
                        contentDescription = "ic_copy",
                        modifier = Modifier.size(20.dp),
                        tint = colors.black
                    )
                }
            }

            if (product.sale_price != null) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .height(0.35.dp),
                    Color(0xFFB9B9B9)
                )
            }
        }

        if (product.sale_price != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
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
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = colors.black,
                        textDecoration = TextDecoration.LineThrough
                    ),
                    modifier = Modifier.padding(start = 10.dp)
                )
                Text(
                    text = "(-${100 - ((product.sale_price!! * 100) / product.sale_old_price!!)}%)",
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = colors.black,
                    ),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .height(0.35.dp),
            Color(0xFFB9B9B9)
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
                            .size(20.dp)
                            .clip(RoundedCornerShape(2.dp))
                    )
                    Text(
                        text = product.users_permissions_user!!.username ?: "",
                        style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = colors.black
                        ), modifier = Modifier.padding(start = 6.dp)
                    )
                }
            }

            TextButton(onClick = {
                if (product.shop?.seo_text != null){
                    scope.launch { sheetStateShop.show() }
                } else {
                    viewModelWrapper.viewModel.navigateToShop(product.shop ?: Shop())
                }
            }) {
                Text(
                    text = product.shop?.name ?: "",
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        textDecoration = TextDecoration.Underline,
                        color = colors.black
                    ),
                )
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .height(0.35.dp),
            Color(0xFFB9B9B9)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            RatingView(
                width = 127.dp,
                height = 25.dp,
                boxSize = 20.dp,
                likes = product.count_likes?.toInt() ?: 0,
                dislikes = product.count_dislikes?.toInt() ?: 0
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (product.count_likes != null) {
                    IconButton(
                        modifier = Modifier
                            .width(32.dp)
                            .height(24.dp),
                        onClick = { viewModelWrapper.viewModel.reactionDocument(true) }) {
                        Image(
                            painter = painterResource(
                                id = when (state.value.isLike) {
                                    true -> R.drawable.ic_like_orange
                                    false -> R.drawable.ic_like
                                    else -> R.drawable.ic_like
                                }
                            ),
                            contentDescription = "ic_like",
                            modifier = Modifier
                                .size(width = 32.dp, height = 24.dp),
                        )
                    }
                    Text(
                        text = product.count_likes ?: "",
                        style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp,
                            color = colors.black
                        ),
                        modifier = Modifier.padding(start = 8.dp, end = 16.dp),
                    )
                }

                if (product.count_dislikes != null) {
                    IconButton(
                        modifier = Modifier
                            .width(32.dp)
                            .height(24.dp),
                        onClick = { viewModelWrapper.viewModel.reactionDocument(false) }) {
                        Image(
                            painter = painterResource(
                                id = when (state.value.isLike) {
                                    true -> R.drawable.ic_dislike
                                    false -> R.drawable.ic_dislike_orange
                                    else -> R.drawable.ic_dislike
                                }
                            ),
                            contentDescription = "ic_dislike",
                            modifier = Modifier
                                .size(width = 32.dp, height = 24.dp),
                        )
                    }
                    Text(
                        text = product.count_dislikes ?: "", style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp,
                            color = colors.black
                        ), modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 15.dp)
                .height(0.35.dp),
            Color(0xFFB9B9B9)
        )

        Text(
            text = product.description ?: "",
            style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = colors.black,
            ),
        )

        Column(Modifier.padding(top = 15.dp)) {
            DefaultButton(title = "Комментарии", background = colors.white) {
                scope.launch { sheetStateComment.show() }
            }
        }

        Column(Modifier.padding(top = 10.dp)) {
            GradientButton(Modifier.fillMaxWidth(), title = "Перейти к скидке") {
                viewModelWrapper.viewModel.openProductUrl(product)
            }
        }
    }
}
