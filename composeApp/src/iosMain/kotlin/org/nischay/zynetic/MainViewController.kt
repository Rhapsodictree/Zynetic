package org.nischay.zynetic

import androidx.compose.ui.window.ComposeUIViewController
import org.nischay.zynetic.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin {
        // iOS-specific Koin configuration if needed
    }

    App()
}