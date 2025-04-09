package org.nischay.zynetic.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.nischay.zynetic.data.remote.ProductApi
import org.nischay.zynetic.data.repository.ProductRepo
import org.nischay.zynetic.data.repository.ProductRepoImpl
import org.nischay.zynetic.domain.usecase.GetProductByIdUseCase
import org.nischay.zynetic.domain.usecase.GetProductsUseCase
import org.nischay.zynetic.presentation.productdetail.ProductDetailViewModel
import org.nischay.zynetic.presentation.productlist.ProductListViewModel

val appModule = module {
    single { ProductApi() }
    single<ProductRepo> { ProductRepoImpl(get()) }
    factory { GetProductsUseCase(get()) }
    factory { GetProductByIdUseCase(get()) }
    viewModel { ProductListViewModel(get()) }
    viewModel { (productId: Int) -> ProductDetailViewModel(get(), productId) }
}