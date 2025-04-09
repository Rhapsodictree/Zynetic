package org.nischay.zynetic.data.mapper

import org.nischay.zynetic.domain.model.Product
import org.nischay.zynetic.data.model.ProductDto

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand,
        category = category,
        thumbnail = thumbnail,
        images = images
    )
}

fun List<ProductDto>.toDomainList(): List<Product> {
    return map { it.toDomain() }
}