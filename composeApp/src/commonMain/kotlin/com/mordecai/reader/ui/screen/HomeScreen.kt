package com.mordecai.reader.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(30.dp)
                .background(Color.Blue)
        )
        val current = LocalNavigator.current
    }
}