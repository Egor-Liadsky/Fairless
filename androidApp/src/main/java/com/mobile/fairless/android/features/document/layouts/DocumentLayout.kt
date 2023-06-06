package com.mobile.fairless.android.features.document.layouts

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
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
import com.mobile.fairless.android.R
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.document.components.FireProductItem
import com.mobile.fairless.android.features.views.buttons.CommonButton
import com.mobile.fairless.android.features.views.buttons.CommonButtonParams
import com.mobile.fairless.android.features.views.buttons.ShapeButton
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.common.viewModel.StatefulKmpViewModel
import com.mobile.fairless.features.document.state.DocumentState
import com.mobile.fairless.features.document.viewModel.DocumentViewModel
import com.mobile.fairless.features.main.models.ProductData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DocumentLayout(
    product: ProductData,
    sheetState: ModalBottomSheetState,
    viewModelWrapper: StatefulViewModelWrapper<DocumentViewModel, DocumentState>
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

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
                    IconButton(
                        modifier = Modifier.padding(end = 3.dp),
                        onClick = { viewModelWrapper.viewModel.reactionDocument(true) }) {
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
                        onClick = { viewModelWrapper.viewModel.reactionDocument(false) }) {
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

        if (product.promo_code != null) {
            Column(modifier = Modifier
                .border(0.5.dp, colors.black, RoundedCornerShape(5.dp))
                .clip(
                    RoundedCornerShape(5.dp)
                )
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
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp, top = 10.dp),
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
                Text(text = product.users_permissions_user!!.username ?: "")
            }
        }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            ShapeButton(title = "Комментарии", modifier = Modifier.padding(top = 10.dp)) {
                scope.launch { sheetState.show() }
            }

            CommonButton(
                commonButtonParams = CommonButtonParams(
                    title = "Перейти к скидке",
                    titleColor = colors.white,
                    background = colors.orangeMain
                ), modifier = Modifier.padding(top = 10.dp)
            ) {
                viewModelWrapper.viewModel.openProductUrl(product)
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
