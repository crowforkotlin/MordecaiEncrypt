package com.crow.mordecai.encrypt.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(scrollBehavior: TopAppBarScrollBehavior) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(100) {
            Text("World : $it")
        }

    }
}