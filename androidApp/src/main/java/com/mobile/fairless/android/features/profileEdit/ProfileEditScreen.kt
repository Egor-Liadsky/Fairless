package com.mobile.fairless.android.features.profileEdit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.profileEdit.layouts.ProfileEditLayout
import com.mobile.fairless.android.features.views.topBars.CommonTopBar
import com.mobile.fairless.features.profileEdit.viewModel.ProfileEditViewModel
import org.koin.androidx.compose.get
import org.koin.core.qualifier.named

@Composable
fun ProfileEditScreen(viewModelWrapper: ViewModelWrapper<ProfileEditViewModel> = get(named("ProfileEditViewModel"))) {
    Column(Modifier.fillMaxSize()) {
        CommonTopBar(title = "Редактирование\nпрофиля", isBack = true, isSave = true) {
            viewModelWrapper.viewModel.onBackButtonClick()
        }
        ProfileEditLayout()
    }
}
