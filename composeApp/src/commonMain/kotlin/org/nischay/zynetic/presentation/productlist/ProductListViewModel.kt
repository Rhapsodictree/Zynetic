package org.nischay.zynetic.presentation.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.nischay.zynetic.domain.model.Product
import org.nischay.zynetic.domain.usecase.GetProductsUseCase

class ProductListViewModel(private val getProductsUseCase: GetProductsUseCase) : ViewModel() {
    private val _state = MutableStateFlow(ProductListState(isLoading = true))
    val state: StateFlow<ProductListState> = _state

    init {
        loadProducts()
    }

    fun loadProducts(limit: Int = 30, skip: Int = 0) {
        viewModelScope.launch {
            _state.value = ProductListState(isLoading = true)
            getProductsUseCase(limit, skip)
                .onSuccess { products ->
                    _state.value = ProductListState(products = products, isLoading = false)
                }
                .onFailure { error ->
                    _state.value = ProductListState(error = error.message ?: "Unknown error", isLoading = false)
                }
        }
    }
}
