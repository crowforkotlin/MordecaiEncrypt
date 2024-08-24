package com.crow.mordecai.encrypt.ui.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.crow.mordecai.encrypt.ext.log
import com.crow.mordecai.encrypt.ui.navigation.Screen
import com.crow.mordecai.encrypt.ui.theme.MordecaiFileEncryptTheme


private val screens = listOf(
    Screen.Home,
    Screen.Setting
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun AppMainScreen() {
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        bottomBar = { MordecaiBottomNavigation(screens, navController) },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.mRoute,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.mRoute) { HomeScreen(scrollBehavior) }
            composable(Screen.Setting.mRoute) { SettingScreen(scrollBehavior) }
        }
    }
}


@ExperimentalAnimationApi
@Composable
fun MordecaiBottomNavigation(
    items: List<Screen>, navController: NavController
) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            val isSelected =
                currentDestination?.hierarchy?.any { it.route == screen.mRoute } == true
            NavigationBarItem(
                selected = isSelected,
                icon = { Icon(screen.mIcon, contentDescription = null) },
                alwaysShowLabel = false,
                label = {
                    isSelected.log()
                    Surface(
                        shape = CircleShape,
                        modifier = Modifier.size(12.dp),
                        color = Color(0xFF1CD0E7)
                    ) { }
                },
                onClick = {
                    navController.navigate(screen.mRoute) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }

        /*for (index in 0..1) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        onClick = {
                            selectedItem = index
                        },
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NavigationIcon(index, selectedItem)
                Spacer(Modifier.padding(top = 2.dp))
                AnimatedVisibility(visible = index == selectedItem) {
                    Surface(
                        shape = CircleShape,
                        modifier = Modifier.size(12.dp),
                        color = Color(0xFF1CD0E7)
                    ) { }
                }
            }
        }
        * */
    }
}

@Composable
@Preview(name = "MordecaiFileEncrpt", showBackground = true)
fun App() {
    MordecaiFileEncryptTheme {
        AppMainScreen()
    }
}