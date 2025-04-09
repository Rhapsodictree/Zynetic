package org.nischay.zynetic.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.nischay.zynetic.presentation.productdetail.ProductDetailScreen
import org.nischay.zynetic.presentation.productlist.ProductListScreen

object ProductListScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        ProductListScreen(
            onProductClick = { productId ->
                navigator.push(ProductDetailScreen(productId))
            }
        )
    }
}

data class ProductDetailScreen(val productId: Int) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        ProductDetailScreen(
            productId = productId,
            onBackClick = { navigator.pop() }
        )
    }
}