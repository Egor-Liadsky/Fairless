package com.mobile.fairless.android.features.main.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mobile.fairless.features.main.models.ProductData

@Composable
fun ProductItem(product: ProductData) {
    Card(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .width(350.dp)
            .height(200.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            AsyncImage(
                model = "https://api.fairless.ru" + product.image.url,
                contentDescription = "product_image",
                modifier = Modifier
                    .width(100.dp)
                    .height(160.dp)
            )

            Column() {
                Text(text = product.name)
                Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                    Text(text = product.sale_price.toString())
                    Text(text = product.sale_old_price.toString())
                }
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(text = product.shop.name)
                    Text(text = product.count_likes)
                    Text(text = product.count_dislikes)
                }
                Text(text = product.users_permissions_user.username)
            }

            Log.e("lqiwjeoiqwueoiqwue", product.image.url)
        }
    }
}
