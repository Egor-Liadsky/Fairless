package com.mobile.fairless.android.features.additional.Feedback

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.fairless.R
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.views.buttons.GradientButton
import com.mobile.fairless.android.features.views.topBars.CommonTopBar
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.android.theme.fontQanelas
import com.mobile.fairless.features.additional.aboutFairLess.viewModel.FeedbackViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun FeedbackScreen(viewModelWrapper: ViewModelWrapper<FeedbackViewModel> = get(named("FeedbackViewModel"))) {

    val context = LocalContext.current

    Column(Modifier.fillMaxSize()) {
        Column(Modifier.background(colors.navBar)) {
            CommonTopBar(
                title = stringResource(id = R.string.feedback),
                backClick = { viewModelWrapper.viewModel.onBackButtonClick() })
        }

        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "У вас есть вопрос, предложение или жалоба?\nНапишите нам!",
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = colors.black,
                    textAlign = TextAlign.Center
                ), modifier = Modifier.padding(bottom = 20.dp)
            )

            GradientButton(title = "Написать") {
                val emailIntent = Intent(Intent.ACTION_SENDTO)
                emailIntent.data = Uri.parse("mailto:")
                emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("support@fairless.ru"))
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Обратная связь")
                context.startActivity(Intent.createChooser(emailIntent, "Обратная связь"))
            }
        }
    }
}
