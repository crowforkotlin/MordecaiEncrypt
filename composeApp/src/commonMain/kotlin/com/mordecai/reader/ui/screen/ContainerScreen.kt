package com.mordecai.reader.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.CrossfadeTransition
import mordecaix.composeapp.generated.resources.Res
import mordecaix.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

class ContainerScreen() : Screen {

    @Composable
    override fun Content() {
        val currentNav = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = { },
            content = {
                Navigator(HomeScreen()) { navigator -> CrossfadeTransition(navigator) }
            },
            bottomBar = {
                BottomNavigation(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight()
                ) {
                    BottomNavigationItem(
                        selected = true,
                        onClick = { currentNav.push(Thirdcreen()) },
                        icon = {
                            Image(
                                painter = painterResource(Res.drawable.compose_multiplatform),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(56.dp).padding(5.dp)
                            )
                        }
                    )
                }
            }
        )
    }
}