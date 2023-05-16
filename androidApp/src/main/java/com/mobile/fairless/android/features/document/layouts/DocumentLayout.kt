package com.mobile.fairless.android.features.document.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.main.models.Product

@Composable
fun DocumentLayout(product: Product) {

    Text(text = product.toString())
}
