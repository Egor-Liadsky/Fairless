package com.mobile.fairless.android.features.additional.Rules

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
import com.mobile.fairless.features.additional.aboutFairLess.viewModel.RulesViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun RulesScreen(viewModelWrapper: ViewModelWrapper<RulesViewModel> = get(named("RulesViewModel"))) {
    Column(Modifier.fillMaxSize()) {
        Column(Modifier.background(colors.navBar)) {
            CommonTopBar(
                title = stringResource(id = R.string.rules),
                backClick = { viewModelWrapper.viewModel.onBackButtonClick() })
        }

        AboutBlockView()

    }
}
