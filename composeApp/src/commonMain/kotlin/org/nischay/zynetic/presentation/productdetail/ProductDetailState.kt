package org.nischay.zynetic.presentation.productdetail

import org.nischay.zynetic.domain.model.Product

data class ProductDetailState(
    val product: Product? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)