package com.mobile.fairless.features.main.viewModel

import com.mobile.fairless.common.navigation.Navigator
import com.mobile.fairless.common.storage.PrefService
import com.mobile.fairless.common.utils.UrlEncode
import com.mobile.fairless.common.viewModel.KmpViewModel
import com.mobile.fairless.common.viewModel.KmpViewModelImpl
import com.mobile.fairless.common.viewModel.SubScreenViewModel
import com.mobile.fairless.features.main.models.Category
import com.mobile.fairless.features.main.models.ProductData
import com.mobile.fairless.features.main.service.MainService
import com.mobile.fairless.features.main.state.MainState
import com.mobile.fairless.features.mainNavigation.service.ErrorService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface MainViewModel : KmpViewModel, SubScreenViewModel {
    val state: StateFlow<MainState>

    fun getCategories()
    fun getProductsByCategory()
    fun selectCategory(category: Category)
    fun onDocumentClick(product: String)
    fun onProfileClick()
}

class MainViewModelImpl(override val navigator: Navigator) : KmpViewModelImpl(), KoinComponent,
    MainViewModel {

    private val mainService: MainService by inject()
    private val errorService: ErrorService by inject()
    private val prefService: PrefService by inject()
    private val urlEncode: UrlEncode by inject()

    private val _state = MutableStateFlow(MainState())
    override val state: StateFlow<MainState> = _state.asStateFlow()

    override fun onViewShown() {
        super.onViewShown()
        getCategories()
        if (_state.value.products.data == null) {
            getProductsByCategory()
        }
    }

    override fun getCategories() {
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    _state.update { it.copy(categoriesLoading = true) }
                    if (_state.value.categories == null) {
                        val categories = mainService.getCategories().toMutableList()
                        val indexAllCategory = categories.indexOfFirst { it.url == "all" }

                        if (indexAllCategory != -1) { // Перенос "Все категории" на первую позицию в списке
                            val elementToMove = categories[indexAllCategory]
                            categories.removeAt(indexAllCategory)
                            categories.add(0, elementToMove)
                        }
                        _state.update { it.copy(categories = categories) }
                    }
                },
                failureBlock = {
                    errorService.showError("Ошибка загрузки. Проверьте подключение к сети и повторите попытку.")
                },
                completionBlock = {
                    _state.update { it.copy(categoriesLoading = false) }
                }
            )
        }
    }

    override fun getProductsByCategory() {
        setLoading(true)
        scope.launch {
            exceptionHandleable(
                executionBlock = {
                    val data = mainService.getProductsByCategory(_state.value.selectCategory.url ?: "all")
                    if (data.data != null) {
                        _state.update { it.copy(products = data) }
                    }
                },
                completionBlock = { setLoading(false) }
            )
        }
    }

    override fun selectCategory(category: Category) {
        _state.update { it.copy(selectCategory = category) }
        getProductsByCategory()
    }

    override fun onDocumentClick(product: String) {
        val document = Json.encodeToString(product)
        val encodeUrl = urlEncode.encodeToUrl(document)
        navigator.navigateToDocument(encodeUrl)
    }

    override fun onProfileClick() {
        val data = prefService.getUserInfo()
        if (data?.user == null) {
            navigator.navigateToAuth()
        } else {
            navigator.navigateToProfile()
        }
    }

    private fun setLoading(isLoading: Boolean) {
        _state.update { it.copy(productsLoading = isLoading) }
    }
}
