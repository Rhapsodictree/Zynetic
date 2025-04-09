package org.nischay.zynetic.data.repository

import org.nischay.zynetic.domain.model.Product

interface ProductRepo {
    suspend fun getAllProducts(limit: Int, skip: Int): Result<List<Product>>
    suspend fun getProductById(id: Int): Result<Product>
    suspend fun searchProducts(query: String): Result<List<Product>>
    suspend fun getProductCategories(): Result<List<String>>
    suspend fun getProductsByCategory(category: String): Result<List<Product>>
}