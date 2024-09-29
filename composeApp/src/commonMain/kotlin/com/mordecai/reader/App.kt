package com.mordecai.reader

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.mordecai.reader.ui.screen.ContainerScreen
import com.mordecai.reader.ui.theme.MordecaiTheme
import io.ktor.util.InternalAPI
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(InternalAPI::class, ExperimentalMaterialApi::class)
@Composable
@Preview
fun App() {
    MordecaiTheme {
        Navigator(ContainerScreen()) { navigator -> FadeTransition(navigator) }
    }
}