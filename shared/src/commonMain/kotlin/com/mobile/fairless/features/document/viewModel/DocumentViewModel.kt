package com.mobile.fairless.features.document.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.utils.UrlEncode
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.document.model.ShareInfo
import com.mobile.fairless.features.document.state.DocumentState
import com.mobile.fairless.features.main.models.Product
import com.mobile.fairless.features.main.models.ProductData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


interface DocumentViewModel : KmpViewModel, SubScreenViewModel {
    val state: StateFlow<DocumentState>
    val shareText: SharedFlow<ShareInfo>
    val openUrl: SharedFlow<ShareInfo>

    fun decodeProduct(product: String)
    fun onShareClick(product: ProductData)
    fun openProductUrl(product: ProductData)

}

class DocumentViewModelImpl(override val navigator: Navigator) : KmpViewModelImpl(), KoinComponent,
    DocumentViewModel {

    private val _state = MutableStateFlow(DocumentState())
    override val state: StateFlow<DocumentState> = _state.asStateFlow()

    private val urlEncode: UrlEncode by inject()

    private val mutableShareText = MutableSharedFlow<ShareInfo>()
    override val shareText: SharedFlow<ShareInfo> = mutableShareText

    private val mutableOpenUrl = MutableSharedFlow<ShareInfo>()
    override val openUrl: SharedFlow<ShareInfo> = mutableOpenUrl

    override fun decodeProduct(product: String) {
        _state.update {
            val decodeProduct = urlEncode.decodeToUrl(product)
            it.copy(product = Json.decodeFromString(decodeProduct))
        }
    }

    override fun onShareClick(product: ProductData) {
        scope.launch {
            mutableShareText.emit(ShareInfo(product.name ?: "", "${product.name}\n${product.sale_url}"))
        }
    }

    override fun openProductUrl(product: ProductData) {
        scope.launch {
            mutableOpenUrl.emit(ShareInfo(product.name ?: "", product.sale_url ?: ""))
        }
    }
}

