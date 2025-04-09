package org.nischay.zynetic.domain.usecase

import org.nischay.zynetic.data.repository.ProductRepo
import org.nischay.zynetic.domain.model.Product

class GetProductsUseCase(private val repository: ProductRepo) {
    suspend operator fun invoke(limit: Int = 30, skip: Int = 0): Result<List<Product>> {
        return repository.getAllProducts(limit, skip)
    }
}