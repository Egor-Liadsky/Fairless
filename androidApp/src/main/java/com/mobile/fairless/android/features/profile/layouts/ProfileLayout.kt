package com.mobile.fairless.android.features.profile.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mobile.fairless.android.R
import com.mobile.fairless.android.di.StatefulViewModelWrapper
import com.mobile.fairless.android.features.profile.components.ExitButton
import com.mobile.fairless.android.features.views.buttons.SquareButton
import com.mobile.fairless.android.theme.colors
import com.mobile.fairless.features.profile.state.ProfileButton
import com.mobile.fairless.features.profile.state.ProfileState
import com.mobile.fairless.features.profile.viewModel.ProfileViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileLayout(viewModelWrapper: StatefulViewModelWrapper<ProfileViewModel, ProfileState>) {

    val state = viewModelWrapper.state

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val scope = rememberCoroutineScope()

    val personalDataButtonList = listOf(
        ProfileCategoryItem(title = "Логин", placeholder = state.value.user?.user?.username ?: "Пусто", onClick = {
                viewModelWrapper.viewModel.selectButton(ProfileButton.LOGIN)
                scope.launch { sheetState.show() }
            }),
        ProfileCategoryItem(title = "Город", placeholder = state.value.user?.user?.city?.name ?: "Пусто", onClick = {
            viewModelWrapper.viewModel.selectButton(ProfileButton.CITY)
            scope.launch { sheetState.show() }
        }),
    )

    val accountButtonList = listOf(
        ProfileCategoryItem(title = "E-mail", placeholder = state.value.user?.user?.email ?: "Пусто", onClick = {
            viewModelWrapper.viewModel.selectButton(ProfileButton.EMAIL)
            scope.launch { sheetState.show() }
        }),
        ProfileCategoryItem(title = "Пароль", placeholder = "••••••••••••••••••", onClick = {
            viewModelWrapper.viewModel.selectButton(ProfileButton.PASSWORD)
            scope.launch { sheetState.show() }
        })
    )

    ModalBottomSheetLayout(
        modifier = Modifier.fillMaxSize(),
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
        sheetContent = {

            when (state.value.selectButton) {
                ProfileButton.LOGIN -> {
                    ProfileSheet(
                        sheetState = sheetState,
                        state = state,
                        content = { LoginSheetView(sheetState, state) })
                }

                ProfileButton.CITY -> {
                    ProfileSheet(
                        sheetState = sheetState,
                        state = state,
                        content = { CitySheetView(sheetState, viewModelWrapper) })
                }

                ProfileButton.EMAIL -> {
                    ProfileSheet(
                        sheetState = sheetState,
                        state = state,
                        content = { EmailSheetView(sheetState, state) })
                }

                ProfileButton.PASSWORD -> {
                    ProfileSheet(
                        sheetState = sheetState,
                        state = state,
                        content = { PasswordSheetView(sheetState, state) })
                }

                else -> {}
            }
        },
    ) {
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
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                ExitButton {
                    viewModelWrapper.viewModel.exitUser()
                    viewModelWrapper.viewModel.onBackButtonClick()
                }
            }
        }
    }
}
