package org.nischay.zynetic.presentation.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.nischay.zynetic.domain.model.Product
import org.nischay.zynetic.domain.usecase.GetProductByIdUseCase

class ProductDetailViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val productId: Int
) : ViewModel() {
    private val _state = MutableStateFlow(ProductDetailState(isLoading = true))
    val state: StateFlow<ProductDetailState> = _state

    init {
        loadProductDetails()
    }

    fun loadProductDetails() {
        viewModelScope.launch {
            _state.value = ProductDetailState(isLoading = true)
            getProductByIdUseCase(productId)
                .onSuccess { product ->
                    _state.value = ProductDetailState(product = product, isLoading = false)
                }
                .onFailure { error ->
                    _state.value = ProductDetailState(error = error.message ?: "Unknown error", isLoading = false)
                }
        }
    }
}
