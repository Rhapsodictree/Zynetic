package org.nischay.zynetic.data.remote

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.nischay.zynetic.data.model.ProductDto
import org.nischay.zynetic.data.model.ProductsResponse

class ProductApi {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    private val baseUrl = "https://dummyjson.com"

    suspend fun getAllProducts(limit: Int = 30, skip: Int = 0): Result<ProductsResponse> = runCatching {
        client.get("$baseUrl/products") {
            parameter("limit", limit)
            parameter("skip", skip)
        }.body()
    }

    suspend fun getProductById(id: Int): Result<ProductDto> = runCatching {
        client.get("$baseUrl/products/$id").body()
    }

    suspend fun searchProducts(query: String): Result<ProductsResponse> = runCatching {
        client.get("$baseUrl/products/search") {
            parameter("q", query)
        }.body()
    }

    suspend fun getProductCategories(): Result<List<String>> = runCatching {
        client.get("$baseUrl/products/categories").body()
    }

    suspend fun getProductsByCategory(category: String): Result<ProductsResponse> = runCatching {
        client.get("$baseUrl/products/category/$category").body()
    }
}