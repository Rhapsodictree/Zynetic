package org.nischay.zynetic.domain.usecase

import org.nischay.zynetic.data.repository.ProductRepo
import org.nischay.zynetic.domain.model.Product

class GetProductByIdUseCase(private val repository: ProductRepo) {
    suspend operator fun invoke(id: Int): Result<Product> {
        return repository.getProductById(id)
    }
}