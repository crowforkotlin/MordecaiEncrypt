package com.mordecai.reader

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.mordecai.reader.model.di.networkModule
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(
            networkModule
        )
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "MordecaiRouter",
    ) {
        App()
    }
}