package com.mobile.fairless.features.document.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.utils.UrlEncode
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.document.state.DocumentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


interface DocumentViewModel : KmpViewModel, SubScreenViewModel {
    val state: StateFlow<DocumentState>
    fun decodeProduct(product: String)
}

class DocumentViewModelImpl(override val navigator: Navigator) : KmpViewModelImpl(), KoinComponent,
    DocumentViewModel {

    private val _state = MutableStateFlow(DocumentState())
    override val state: StateFlow<DocumentState> = _state.asStateFlow()

    private val urlEncode: UrlEncode by inject()

    override fun decodeProduct(product: String) {
        _state.update {
            val decodeProduct = urlEncode.decodeToUrl(product)
            it.copy(product = Json.decodeFromString(decodeProduct))
        }
    }
}

