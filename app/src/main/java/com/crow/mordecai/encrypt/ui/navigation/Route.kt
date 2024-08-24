package com.crow.mordecai.encrypt.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.crow.mordecai.encrypt.R

sealed class Screen(val mRoute: String, @StringRes val mResourceId: Int, val mIcon: ImageVector) {
    data object Home : Screen("profile", R.string.homepage, Icons.Rounded.Home)
    data object Setting : Screen("friendslist", R.string.setting, Icons.Rounded.Settings)
}