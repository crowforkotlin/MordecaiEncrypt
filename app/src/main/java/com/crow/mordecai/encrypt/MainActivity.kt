package com.crow.mordecai.encrypt

import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.crow.mordecai.encrypt.ext.dpToPx
import com.crow.mordecai.encrypt.ext.log
import com.crow.mordecai.encrypt.ui.Screen
import com.crow.mordecai.encrypt.ui.theme.MordecaiFileEncryptTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val isLaunch = savedInstanceState == null
        val splash = if (isLaunch) installSplashScreen() else null
        super.onCreate(savedInstanceState)
        setSplashScreenExitAnimation(splash)
        enableEdgeToEdge()
        setContent {
            MordecaiFileEncryptTheme {
                App()
            }
        }
    }

    private fun setSplashScreenExitAnimation(splashScreen: SplashScreen?) {
        val root = findViewById<View>(android.R.id.content)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S && splashScreen != null) {
            splashScreen.setOnExitAnimationListener { splashProvider ->
                // For some reason the SplashScreen applies (incorrect) Y translation to the iconView
                splashProvider.iconView.translationY = 0F

                val activityAnim = ValueAnimator.ofFloat(1F, 0F).apply {
                    interpolator = LinearOutSlowInInterpolator()
                    duration = 1000L
                    addUpdateListener { va ->
                        val value = va.animatedValue as Float
                        root.translationY = value * 16.dpToPx
                    }
                }

                val splashAnim = ValueAnimator.ofFloat(1F, 0F).apply {
                    interpolator = FastOutSlowInInterpolator()
                    duration = 1000L
                    addUpdateListener { va ->
                        val value = va.animatedValue as Float
                        splashProvider.view.alpha = value
                    }
                    doOnEnd {
                        splashProvider.remove()
                    }
                }

                activityAnim.start()
                splashAnim.start()
            }
        }
    }
}

private val screens = listOf(
    Screen.Home,
    Screen.Setting
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            MyBottomNavigation(screens, navController)
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.Home.mRoute, modifier = Modifier.padding(innerPadding)) {
            composable(Screen.Home.mRoute) { Text("Hello") }
            composable(Screen.Setting.mRoute) { Text("World")  }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MordecaiFileEncryptTheme {
        App()
    }
}

@ExperimentalAnimationApi
@Composable
fun MyBottomNavigation(
    items: List<Screen>,
    navController: NavController
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
                        modifier = Modifier
                            .size(12.dp),
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