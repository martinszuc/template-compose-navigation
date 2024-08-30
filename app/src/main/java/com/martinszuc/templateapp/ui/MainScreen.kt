package com.martinszuc.templateapp.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.martinszuc.templateapp.ui.component.settings.SettingsViewModel
import com.martinszuc.templateapp.ui.navigation.BottomNavItem
import com.martinszuc.templateapp.ui.navigation.BottomNavigationBar
import com.martinszuc.templateapp.ui.navigation.NavGraph
import com.martinszuc.templateapp.ui.navigation.Screen
import com.martinszuc.templateapp.ui.theme.AppTheme
import com.martinszuc.templateapp.utils.AppConstants.THEME_DARK
import com.martinszuc.templateapp.utils.AppConstants.THEME_SYSTEM_DEFAULT

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

@Composable
fun MainScreen() {
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    val themePreference by settingsViewModel.themePreference.collectAsState(initial = THEME_SYSTEM_DEFAULT)

    val isDarkTheme =
        themePreference == THEME_DARK || (themePreference == THEME_SYSTEM_DEFAULT && isSystemInDarkTheme())

    AppTheme(darkTheme = isDarkTheme) {
        val navController = rememberNavController()
        val bottomNavItems = listOf(
            BottomNavItem(Screen.ScreenOne.route, Icons.Filled.Home),
            BottomNavItem(Screen.ScreenTwo.route, Icons.AutoMirrored.Filled.List),
            BottomNavItem(Screen.Menu.route, Icons.Filled.Menu),
            BottomNavItem(Screen.Settings.route, Icons.Filled.Settings)
        )

        Scaffold(
            bottomBar = { BottomNavigationBar(navController, bottomNavItems) }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavGraph(
                    navController = navController,
                    onThemeChanged = { newTheme ->
                        settingsViewModel.changeTheme(newTheme)
                    }
                )
            }
        }
    }
}