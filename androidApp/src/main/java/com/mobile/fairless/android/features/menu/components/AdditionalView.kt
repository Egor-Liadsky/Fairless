Ipackage com.mobile.fairless.android.features.menu.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.R
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.menu.state.MenuState
import com.mobile.fairless.features.menu.viewModel.MenuViewModel

data class AdditionalInfo(
    val title: String,
    val onClick: () -> Unit
)

@Composable
fun AdditionalView(viewModelWrapper: StatefulViewModelWrapper<MenuViewModel, MenuState>) {
    val list = listOf(
        AdditionalInfo(title = stringResource(id = R.string.about), onClick = { viewModelWrapper.viewModel.navigateToAboutFairLess() }),
        AdditionalInfo(title = stringResource(id = R.string.rules), onClick = { viewModelWrapper.viewModel.navigateToRules() }),
        AdditionalInfo(title = stringResource(id = R.string.feedback), onClick = { viewModelWrapper.viewModel.navigateToFeedback() }),
        AdditionalInfo(title = "FAQ", onClick = { viewModelWrapper.viewModel.navigateToFaq() }),
        AdditionalInfo(title = stringResource(id = R.string.about_app), onClick = { viewModelWrapper.viewModel.navigateToAboutApp() }),
    )

    Column(
        Modifier
            .padding(top = 20.dp, start = 16.dp, end = 16.dp)
            .border(1.dp, colors.navBar, RoundedCornerShape(5.dp))
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 25.dp)
        ) {
            Text(
                text = "Дополнительно", style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = colors.black
                ), modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
            )
            list.forEach {
                AdditionalItem(additionalInfo = it)
            }
        }
    }
}

@Composable
fun AdditionalItem(additionalInfo: AdditionalInfo) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable {
                additionalInfo.onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_square),
                contentDescription = "ic_square",
                modifier = Modifier.size(width = 18.dp, height = 20.dp),
                tint = Color(0xFFA8ACAF)
            )
            Text(
                text = additionalInfo.title, style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = colors.black
                ), modifier = Modifier.padding(start = 18.dp)
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_next), contentDescription = "ic_next",
                modifier = Modifier.size(width = 5.dp, height = 11.dp),
                tint = colors.orangeMain
            )
        }
    }
}
