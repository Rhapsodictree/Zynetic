package org.nischay.zynetic.data.repository

import org.nischay.zynetic.data.mapper.toDomain
import org.nischay.zynetic.data.mapper.toDomainList
import org.nischay.zynetic.domain.model.Product
import org.nischay.zynetic.data.remote.ProductApi
import org.nischay.zynetic.data.repository.ProductRepo

class ProductRepoImpl(private val api: ProductApi) : ProductRepo {
    override suspend fun getAllProducts(limit: Int, skip: Int): Result<List<Product>> = runCatching {
        api.getAllProducts(limit, skip).getOrThrow().products.toDomainList()
    }

    override suspend fun getProductById(id: Int): Result<Product> = runCatching {
        api.getProductById(id).getOrThrow().toDomain()
    }

    override suspend fun searchProducts(query: String): Result<List<Product>> = runCatching {
        api.searchProducts(query).getOrThrow().products.toDomainList()
    }

    override suspend fun getProductCategories(): Result<List<String>> = runCatching {
        api.getProductCategories().getOrThrow()
    }

    override suspend fun getProductsByCategory(category: String): Result<List<Product>> = runCatching {
        api.getProductsByCategory(category).getOrThrow().products.toDomainList()
    }
}