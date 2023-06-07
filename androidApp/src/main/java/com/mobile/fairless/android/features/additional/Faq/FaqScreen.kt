package com.mobile.fairless.android.features.additional.Faq

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mobile.fairless.android.R
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.additional.AboutFairLess.AboutBlockView
import com.mobile.fairless.android.features.views.topBars.CommonTopBar
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.additional.aboutFairLess.viewModel.FaqViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun FaqScreen(viewModelWrapper: ViewModelWrapper<FaqViewModel> = get(named("FaqViewModel"))) {
    Column(Modifier.fillMaxSize()) {
        Column(Modifier.background(colors.navBar)) {
            CommonTopBar(
                title = stringResource(id = R.string.faq),
                backClick = { viewModelWrapper.viewModel.onBackButtonClick() })
        }

        AboutBlockView()

    }
}
