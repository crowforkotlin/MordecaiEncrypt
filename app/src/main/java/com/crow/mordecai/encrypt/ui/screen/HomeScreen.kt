package com.crow.mordecai.encrypt.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.canlioya.appbarlayoutcompose.FlexibleTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(scrollBehavior: TopAppBarScrollBehavior) {
    Column {
        FlexibleTopBar(scrollBehavior = scrollBehavior, modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            Column(modifier = Modifier.fillMaxWidth().wrapContentHeight().background(Color.Blue)) {
                Text("0.3f")
                Text("0.3f")
                Text("0.3f")
                Text("0.3f")
            }
        }
        LazyColumn(
            modifier = Modifier.weight(0.7f).nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            items(100) {
                Text("Hello : $it")
            }
        }
    }

}