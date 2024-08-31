package com.martinszuc.templateapp.ui.component.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.martinszuc.templateapp.R
import com.martinszuc.templateapp.ui.component.common.AppBarWithoutActions
import com.martinszuc.templateapp.ui.component.common.SquareButtonWithIcon
import com.martinszuc.templateapp.ui.navigation.Screen

@Composable
fun MenuScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            AppBarWithoutActions(
                title = stringResource(R.string.label_menu)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Define the buttons with labels, routes, and icons
            val buttons = listOf(
                Triple(
                    "Screen Three",
                    Screen.ScreenThree.route,
                    Icons.Filled.Search // Change icon as needed
                ),
                Triple(
                    "Screen Four",
                    Screen.ScreenFour.route,
                    Icons.AutoMirrored.Filled.List // Change icon as needed
                )
                // Add more buttons here with appropriate labels, routes, and icons
            )

            // Display buttons in rows of 2
            buttons.chunked(2).forEach { rowButtons ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    rowButtons.forEach { (label, route, icon) ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            SquareButtonWithIcon(
                                label = label,
                                route = route,
                                icon = icon,
                                navController = navController
                            )
                        }
                    }
                    // Add a Spacer to fill the row if there's an odd number of buttons
                    if (rowButtons.size == 1) {
                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}
