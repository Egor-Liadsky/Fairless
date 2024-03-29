package com.mobile.fairless.features.document.viewModel

import com.mobile.fairless.common.analytics.appmetrica.AppMetricaService
import com.mobile.fairless.common.analytics.appmetrica.LogEvent
import com.mobile.fairless.common.analytics.appmetrica.LogEventParam
import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.state.LoadingState
import com.mobile.fairless.common.storage.PrefService
import com.mobile.fairless.common.utils.UrlEncode
import com.mobile.fairless.common.viewModel.StatefulKmpViewModel
import com.mobile.fairless.common.viewModel.StatefulKmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.document.model.ShareInfo
import com.mobile.fairless.features.document.service.DocumentService
import com.mobile.fairless.features.document.state.DocumentState
import com.mobile.fairless.features.document.state.Period
import com.mobile.fairless.features.main.models.DateFilter
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.models.Shop
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import com.mobile.fairless.features.welcome.models.UserReceive
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


interface DocumentViewModel : StatefulKmpViewModel<DocumentState>, SubScreenViewModel {
    val shareText: SharedFlow<ShareInfo>
    val openUrl: SharedFlow<ShareInfo>

    fun onShareClick(product: ProductData)
    fun openProductUrl(product: ProductData)
    fun getFireProducts(period: DateFilter)
    fun onDocumentClick(product: String)
    fun selectFirePeriod(period: DateFilter)
    fun getCommentsByDocument(documentId: String)
    fun sendComment(text: String)
    fun changeCommentText(text: String)
    fun reactionDocument(like: Boolean)
    fun getNameProduct(productName: String, ios: Boolean = false)
    fun periodClick()
    fun navigateToShop(shop: Shop)
    fun reloadDocument()
}

class DocumentViewModelImpl(override val navigator: Navigator) :
    StatefulKmpViewModelImpl<DocumentState>(), KoinComponent,
    DocumentViewModel {

    private val documentService: DocumentService by inject()
    private val errorService: ErrorService by inject()
    private val prefService: PrefService by inject()
    private val urlEncode: UrlEncode by inject()
    private val appMetricaService: AppMetricaService by inject()

    private val _state = MutableStateFlow(DocumentState())
    override val state: StateFlow<DocumentState> = _state.asStateFlow()


    private val mutableShareText = MutableSharedFlow<ShareInfo>()
    override val shareText: SharedFlow<ShareInfo> = mutableShareText

    private val mutableOpenUrl = MutableSharedFlow<ShareInfo>()
    override val openUrl: SharedFlow<ShareInfo> = mutableOpenUrl

    override fun onViewShown() {
        super.onViewShown()
        getDocument()
        getFireProducts(state.value.selectFirePeriod.period)
        checkUser()
        appMetricaService.sendEvent(
            LogEvent.OPEN_SCREEN, mapOf(
                LogEventParam.SCREEN_NAME to "Продукт",
                LogEventParam.SCREEN_CLASS to "DocumentScreen",
                LogEventParam.PRODUCT_ID to state.value.product.id.toString(),
            )
        )
    }

    private fun getDocument() {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    val document = documentService.getDocument(state.value.productName)[0]
                    _state.update { it.copy(product = document) }
                    checkLike()
                    delay(200)
                    _state.update { it.copy(loadingState = LoadingState.Success) }
                },
                failureBlock = { throwable ->
                    _state.update { it.copy(loadingState = LoadingState.Error(throwable.toString())) }
                }
            )
        }
    }

    override fun getNameProduct(productName: String, ios: Boolean) {
        if (!ios) {
            val decodeProduct = urlEncode.decodeToUrl(productName)
            _state.update { it.copy(productName = Json.decodeFromString(decodeProduct)) }
        } else {
            _state.update { it.copy(productName = productName) }
        }
    }

    override fun periodClick() {
        _state.update { it.copy(periodMenuOpen = !it.periodMenuOpen) }
    }

    override fun navigateToShop(shop: Shop) {
        val shopJson = Json.encodeToString(shop)
        val encodeUrl = urlEncode.encodeToUrl(shopJson)
        navigator.navigateToShop(encodeUrl)
    }

    override fun reloadDocument() {
        _state.update { it.copy(refreshable = true) }
        getDocument()
        _state.update { it.copy(refreshable = false) }
    }

    private fun checkLike() {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    val isLike = documentService.checkLike(
                        state.value.product.id ?: "",
                        prefService.getUserInfo()?.user?.id ?: ""
                    )[0].like
                    _state.update { it.copy(isLike = isLike) }
                }
            )
        }
    }

    override fun onShareClick(product: ProductData) {
        scope.launch {
            mutableShareText.emit(
                ShareInfo(
                    product.name ?: "",
                    "${product.name}\n${product.sale_url}"
                )
            )
        }
    }

    override fun openProductUrl(product: ProductData) {
        scope.launch {
            mutableOpenUrl.emit(ShareInfo(product.name ?: "", product.sale_url ?: ""))
        }
    }

    override fun getFireProducts(period: DateFilter) {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    val data = documentService.getFireProducts(4, period)
                    if (data.isEmpty()) {
                        selectFirePeriod(DateFilter.WEEK)
                        _state.update {
                            it.copy(
                                loadingStateFire = LoadingState.Success,
                                todayNull = true
                            )
                        }
                    }
                    _state.update {
                        it.copy(loadingStateFire = LoadingState.Success, fireProduct = data)
                    }
                },
                failureBlock = { throwable ->
                    _state.update { it.copy(loadingStateFire = LoadingState.Error(throwable.toString())) }
                }
            )
        }
    }

    override fun onDocumentClick(product: String) {
        val document = Json.encodeToString(product)
        val encodeUrl = urlEncode.encodeToUrl(document)
        navigator.navigateToDocument(encodeUrl)
    }

    override fun selectFirePeriod(period: DateFilter) {
        _state.update {
            it.copy(
                selectFirePeriod =
                when (period) {
                    DateFilter.TODAY -> Period("Сегодня", DateFilter.TODAY)
                    DateFilter.WEEK -> Period("Неделя", DateFilter.WEEK)
                    DateFilter.MONTH -> Period("Месяц", DateFilter.MONTH)
                }
            )
        }
        getFireProducts(period)
    }

    override fun getCommentsByDocument(documentId: String) {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    _state.update {
                        it.copy(
                            loadingStateComment = LoadingState.Success,
                            comments = documentService.getComments(documentId)
                        )
                    }
                    if (state.value.comments?.isEmpty() == true) {
                        _state.update { it.copy(loadingStateComment = LoadingState.Empty) }
                    }
                },
                failureBlock = { throwable ->
                    _state.update { it.copy(loadingStateComment = LoadingState.Error(throwable.toString())) }
                }
            )
        }
    }

    override fun sendComment(text: String) {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    val user = prefService.getUserInfo()
                    documentService.sendComment(
                        user ?: UserReceive(),
                        text,
                        state.value.product.id ?: ""
                    )
                    getCommentsByDocument(state.value.product.id ?: "")
                },
                failureBlock = {
                    errorService.showError("Ошибка")
                }
            )
        }
    }

    override fun changeCommentText(text: String) {
        _state.update { it.copy(commentText = text) }
    }

    override fun reactionDocument(like: Boolean) {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    if (state.value.authUser) {
                        documentService.reactionDocument(
                            like,
                            state.value.product._id ?: "",
                            prefService.getUserInfo() ?: UserReceive()
                        )
                        getDocument()
                    } else {
                        errorService.showError("Необходимо авторизоваться")
                    }
                },
                failureBlock = {
                    errorService.showError("Ошибка")
                }
            )
        }
    }

    private fun checkUser() {
        if (prefService.getUserInfo()?.user != null) {
            _state.update { it.copy(authUser = true) }
        } else {
            _state.update { it.copy(authUser = false) }
        }
    }
}
