package com.mobile.fairless.android.features.document

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.document.components.DocumentTopBar
import com.mobile.fairless.android.features.document.sheets.CommentSheetView
import com.mobile.fairless.android.features.document.layouts.DocumentLayout
import com.mobile.fairless.android.features.document.layouts.FireProductsLayout
import com.mobile.fairless.android.features.views.layouts.LoadingLayout
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.features.document.state.DocumentState
import com.mobile.fairless.features.document.viewModel.DocumentViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.core.qualifier.named

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DocumentScreen(
    product: String,
    viewModelWrapper: StatefulViewModelWrapper<DocumentViewModel, DocumentState> = getViewModel(
        named("DocumentViewModel")
    )
) {
    viewModelWrapper.viewModel.getNameProduct(product)

    val context = LocalContext.current
    val state = viewModelWrapper.viewModel.state.collectAsState()

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val sheetStateSendComment = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val scope = rememberCoroutineScope()

    BackHandler {
        if (sheetState.isVisible && !sheetStateSendComment.isVisible) {
            scope.launch { sheetState.hide() }
        } else if (sheetStateSendComment.isVisible) {
            scope.launch { sheetStateSendComment.hide() }
        } else {
            viewModelWrapper.viewModel.onBackButtonClick()
        }
    }


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

    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
        sheetContent = {
            CommentSheetView(
                sheetState = sheetState,
                sheetStateSendComment = sheetStateSendComment,
                state = state,
                getChat = {
                    viewModelWrapper.viewModel.getCommentsByDocument(
                        state.value.product.id ?: ""
                    )
                },
                sendCommentOnClick = {
                    viewModelWrapper.viewModel.sendComment(
                        state.value.commentText?.lines()?.joinToString(" ") ?: ""
                    )
                },
                onValueChanged = { viewModelWrapper.viewModel.changeCommentText(it) },
            )
        },
    ) {
        when (state.value.loadingState) {
            LoadingState.Loading -> {
                LoadingLayout()
            }

            LoadingState.Success -> {
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .background(colors.backgroundWelcome)
                ) {
                    item {
                        DocumentTopBar(
                            product = state.value.product,
                            viewModelWrapper = viewModelWrapper
                        )
                    }
                    item {
                        DocumentLayout(
                            product = state.value.product,
                            viewModelWrapper = viewModelWrapper,
                            sheetState = sheetState,
                        )
                    }
                    item {
                        FireProductsLayout(viewModelWrapper = viewModelWrapper)
                    }
                }
            }

            LoadingState.Empty -> {}
            is LoadingState.Error -> {
                Text(text = "error")
            }

            else -> {}
        }
    }
}

