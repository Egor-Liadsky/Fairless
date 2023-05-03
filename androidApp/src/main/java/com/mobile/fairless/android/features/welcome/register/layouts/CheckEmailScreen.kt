package com.mobile.fairless.android.features.welcome.register.layouts

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.views.buttons.CommonButton
import com.mobile.fairless.android.features.views.buttons.CommonButtonParams
import com.mobile.fairless.features.welcome.register.viewModel.RegisterViewModel

@Composable
fun CheckEmailScreen(viewModelWrapper: ViewModelWrapper<RegisterViewModel>) {
    Text(text = "CheckEmailScreen")
    CommonButton(commonButtonParams = CommonButtonParams("Далее")) {
        viewModelWrapper.viewModel.onNextClick()
    }
}
