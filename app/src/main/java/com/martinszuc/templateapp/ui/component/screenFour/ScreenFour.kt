package com.martinszuc.templateapp.ui.component.screenFour


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.martinszuc.templateapp.ui.component.common.AppBarWithoutActions

/**
 * Project: Galerry App
 *
 * Author: Bc. Martin Szuc (matoszuc@gmail.com)
 * GitHub: https://github.com/martinszuc
 *
 *
 * License:
 * This code is licensed under MIT License. You may not use this file except
 * in compliance with the License.
 */

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ScreenFour(
) {
    Scaffold(
        topBar = {
            AppBarWithoutActions(title = "Screen Four")
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            // This is where you can add content later. Currently, it's just an empty box.
        }
    }
}