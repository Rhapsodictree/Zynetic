package org.nischay.zynetic

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.koin.compose.KoinApplication
import org.nischay.zynetic.di.appModule
import org.nischay.zynetic.navigation.ProductListScreen
import org.nischay.zynetic.theme.AppTheme

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        AppTheme {
            Navigator(ProductListScreen)
        }
    }
}