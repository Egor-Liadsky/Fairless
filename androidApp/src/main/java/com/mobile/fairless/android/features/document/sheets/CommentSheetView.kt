package com.mobile.fairless.android.features.document.sheets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.R
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.document.layouts.CommentsEmptyLayout
import com.mobile.fairless.android.features.views.buttons.GradientButton
import com.mobile.fairless.android.features.views.layouts.EmptyLayout
import com.mobile.fairless.android.features.views.layouts.ErrorLayout
import com.mobile.fairless.android.features.views.layouts.LoadingLayout
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.features.document.model.Comment
import com.mobile.fairless.features.document.state.DocumentState
import com.mobile.fairless.features.document.viewModel.DocumentViewModel
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CommentSheetView(
    viewModelWrapper: StatefulViewModelWrapper<DocumentViewModel, DocumentState>,
    sheetStateSendComment: ModalBottomSheetState,
    getChat: () -> Unit,
    errorService: ErrorService = get(),
    sendCommentOnClick: () -> Unit,
    onValueChanged: (String) -> Unit,
) {
    val scope = rememberCoroutineScope()

    val state = viewModelWrapper.state

    getChat()

    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetStateSendComment,
        sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
        sheetContent = {
            SendCommentSheetView(
                sheetState = sheetStateSendComment,
                state = state,
                onClick = { sendCommentOnClick() },
                onValueChanged = { onValueChanged(it) },
            )
        },
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .background(colors.white)
                .padding(start = 16.dp, end = 16.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Divider(
                Modifier
                    .width(30.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .padding(top = 10.dp, bottom = 10.dp),
                color = colors.navBar,
                thickness = 3.dp
            )

            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Комментарии", style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = colors.black
                    ), modifier = Modifier.padding(bottom = 20.dp)
                )


                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {

                    when (state.value.loadingStateComment) {
                        LoadingState.Loading -> {
                            LoadingLayout()
                        }

                        LoadingState.Success -> {
                            LazyColumn(
                                Modifier
                                    .align(Alignment.TopCenter)
                                    .padding(bottom = 60.dp)
                            ) {
                                items(items = state.value.comments ?: emptyList()) { comment ->
                                    CommentItem(
                                        modifier = Modifier.padding(bottom = 5.dp),
                                        comment = comment
                                    )
                                }
                            }

                            GradientButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter),
                                title = "Добавить комментарий"
                            ) {
                                if (state.value.authUser) {
                                    scope.launch { sheetStateSendComment.show() }
                                } else {
                                    scope.launch { errorService.showError("Необходимо авторизоваться") }
                                }
                            }
                        }

                        LoadingState.Empty -> {
                            CommentsEmptyLayout(state.value.authUser)
                            GradientButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter),
                                title = "Добавить комментарий"
                            ) {
                                if (state.value.authUser) {
                                    scope.launch { sheetStateSendComment.show() }
                                } else {
                                   scope.launch { errorService.showError("Необходимо авторизоваться") }
                                }
                            }
                        }

                        is LoadingState.Error -> {
                            ErrorLayout {
                                getChat()
                            }
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}

@Composable
fun CommentItem(modifier: Modifier = Modifier, comment: Comment) {
    Column(
        modifier
            .fillMaxWidth()
            .border(1.dp, colors.navBar, RoundedCornerShape(5.dp))
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatarka),
                    contentDescription = "ic_avatarka",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(5.dp))
                )
                Column(Modifier.padding(start = 16.dp)) {
                    Text(
                        text = comment.users_permissions_user?.username ?: "", style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = colors.black
                        )
                    )
                    Text(
                        text = comment.dateTime ?: "", style = TextStyle(
                            fontFamily = fontQanelas,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                            color = Color(0xFF797979)
                        ), modifier = Modifier.padding(top = 9.dp)
                    )
                }
            }

            Text(
                text = comment.text ?: "", style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = colors.black
                ), modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}
