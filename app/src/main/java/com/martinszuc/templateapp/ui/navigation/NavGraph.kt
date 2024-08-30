package com.martinszuc.templateapp.ui.navigation

/**
 * Project: database application
 *
 * Author: Bc. Martin Szuc (matoszuc@gmail.com)
 * GitHub: https://github.com/martinszuc
 *
 *
 * License:
 * This code is licensed under MIT License. You may not use this file except
 * in compliance with the License.
 */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.martinszuc.templateapp.ui.component.menu.MenuScreen
import com.martinszuc.templateapp.ui.component.screenOne.ScreenOne
import com.martinszuc.templateapp.ui.component.screenTwo.ScreenTwo
import com.martinszuc.templateapp.ui.component.settings.SettingsScreen

sealed class Screen(val route: String) {
    object ScreenOne : Screen("screen_one")
    object ScreenTwo : Screen("screen_two")
    object Menu : Screen("menu")
    object Settings : Screen("settings")
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.ScreenOne.route,
    onThemeChanged: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.ScreenOne.route) {
            ScreenOne()
        }
        composable(Screen.ScreenTwo.route) {
            ScreenTwo()
        }
        composable(Screen.Menu.route) {
            MenuScreen(navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(onThemeChanged = onThemeChanged)
        }
    }
}