package com.mobile.fairless.android.features.document.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.document.model.Comment
import com.mobile.fairless.features.document.state.DocumentState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CommentSheetView(sheetState: ModalBottomSheetState, state: State<DocumentState>) {
    val scope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxSize()
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
                )
            )
            LazyColumn {
                items(items = state.value.comments ?: emptyList()) { comment ->
                    CommentItem(modifier = Modifier.padding(top = 20.dp), comment = comment)
                }
            }
        }
    }
}

@Composable
fun CommentItem(modifier: Modifier = Modifier, comment: Comment) {
    Column(modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatarka),
                contentDescription = "ic_avatarka",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column {
                Text(
                    text = comment.users_permissions_user?.username ?: "", style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = colors.black
                    )
                )
                Text(
                    text = comment.users_permissions_user?.dateTime ?: "", style = TextStyle(
                        fontFamily = fontQanelas,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = colors.black
                    ), modifier = Modifier.padding(top = 10.dp)
                )
            }
        }

        Text(
            text = comment.text ?: "", style = TextStyle(
                fontFamily = fontQanelas,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = colors.black
            ), modifier = Modifier.padding(top = 10.dp)
        )
    }
}
