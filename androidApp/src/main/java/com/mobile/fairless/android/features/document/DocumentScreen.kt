package com.mobile.fairless.android.features.document

import android.content.Intent
import android.content.IntentSender
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.document.components.DocumentTopBar
import com.mobile.fairless.android.features.document.components.FireProductItem
import com.mobile.fairless.android.features.document.layouts.DocumentLayout
import com.mobile.fairless.android.features.document.layouts.FireProductsLayout
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.document.viewModel.DocumentViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun DocumentScreen(
    product: String,
    viewModelWrapper: ViewModelWrapper<DocumentViewModel> = get(named("DocumentViewModel"))
) {

    val context = LocalContext.current
    viewModelWrapper.viewModel.decodeProduct(product)
    val state = viewModelWrapper.viewModel.state.collectAsState()

    viewModelWrapper.viewModel.onViewShown()

    LaunchedEffect(key1 = Unit) {
        viewModelWrapper.viewModel.shareText.collectLatest {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, it.description)
                type = "text/plain"
            }
            context.startActivity(Intent.createChooser(sendIntent, it.title))
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModelWrapper.viewModel.openUrl.collectLatest {
            val openUrlIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.description))
            context.startActivity(Intent.createChooser(openUrlIntent, it.title))
        }
    }

    LazyColumn(
        Modifier
            .fillMaxWidth()
            .background(colors.backgroundWelcome)
    ) {
        item {
            DocumentTopBar(product = state.value.product, viewModelWrapper = viewModelWrapper)
        }
        item {
            DocumentLayout(product = state.value.product, viewModelWrapper = viewModelWrapper)
        }
        item {
            FireProductsLayout(viewModelWrapper = viewModelWrapper)
        }
    }
}
