package com.mobile.fairless.android.features.profile.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.di.ViewModelWrapper
import com.mobile.fairless.android.features.profile.components.ExitButton
import com.mobile.fairless.android.features.profile.components.ProfileCategoryItem
import com.mobile.fairless.android.features.profile.components.ProfileCategoryView
import com.mobile.fairless.android.features.views.buttons.SquareButton
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.profile.viewModel.ProfileViewModel

@Composable
fun ProfileLayout(viewModelWrapper: ViewModelWrapper<ProfileViewModel>) {

    val personalDataButtonList = listOf(
        ProfileCategoryItem(title = "Логин", placeholder = "Иван Иванов", isEnabled = false, onClick = {}),
        ProfileCategoryItem(title = "Город", placeholder = "Ростов-на-Дону", onClick = {}),
    )

    val accountButtonList = listOf(
        ProfileCategoryItem(title = "Email", placeholder = "ivanov2003@gmail.com", onClick = {}),
        ProfileCategoryItem(title = "Пароль", placeholder = "••••••••••••••••••", onClick = {}),
        ProfileCategoryItem(title = "Уведомления", placeholder = "Отключены все", onClick = {}),
    )

    Column(Modifier.fillMaxSize()) {

        SquareButton(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            icon = painterResource(id = R.drawable.ic_back_button),
            backgroundColor = colors.navBar,
            iconSize = 15.dp
        ) {
            viewModelWrapper.viewModel.onBackButtonClick()
        }

        ProfileCategoryView(
            modifier = Modifier.padding(top = 18.dp),
            title = "Личные данные",
            profileCategoryItem = personalDataButtonList
        )
        ProfileCategoryView(
            modifier = Modifier.padding(top = 16.dp),
            title = "Аккаунт",
            profileCategoryItem = accountButtonList
        )
        Box(Modifier.fillMaxSize().padding(bottom = 16.dp),contentAlignment = Alignment.BottomCenter){
            ExitButton() {

            }
        }
    }
}

//data class ProfileCategoryItems(
//    val placeholder: String,
//    val isPassword: Boolean = false,
//    val isCitySelected: Boolean = false,
//    val onValueChanged: () -> Unit,
//    val onClick: () -> Unit
//)
