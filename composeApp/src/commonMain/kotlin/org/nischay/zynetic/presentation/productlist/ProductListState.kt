package org.nischay.zynetic.presentation.productlist

import org.nischay.zynetic.domain.model.Product

data class ProductListState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

