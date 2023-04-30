package com.mobile.fairless.common.viewModel

import com.mobile.fairless.common.navigation.Navigator

interface SubScreenViewModel {
    val navigator: Navigator
    fun onBackButtonClick() {
        navigator.navigateBack()
    }
}
