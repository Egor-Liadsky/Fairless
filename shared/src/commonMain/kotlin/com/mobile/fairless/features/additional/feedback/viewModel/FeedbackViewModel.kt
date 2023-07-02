package com.mobile.fairless.features.additional.aboutFairLess.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.additional.feedback.state.FeedbackState
import com.mobile.fairless.features.additional.feedback.state.ImageFile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

interface FeedbackViewModel : KmpViewModel, SubScreenViewModel {
    val state: StateFlow<FeedbackState>

    fun onValueChanged(text: String)
    fun addFile(imageFile: ImageFile)
    fun deleteFile(imageFile: ImageFile)
}

class FeedbackViewModelImpl(
    override val navigator: Navigator
) : KoinComponent, KmpViewModelImpl(), FeedbackViewModel {

    private val _state = MutableStateFlow(FeedbackState())
    override val state: StateFlow<FeedbackState> = _state.asStateFlow()

    override fun onValueChanged(text: String) {
        _state.update { it.copy(feedbackText = text) }
    }

    override fun addFile(imageFile: ImageFile) {
        state.value.files?.add(imageFile)
    }

    override fun deleteFile(imageFile: ImageFile) {
        state.value.files?.remove(imageFile)
    }
}

