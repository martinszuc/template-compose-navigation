package com.martinszuc.templateapp.ui.component.settings

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.martinszuc.templateapp.utils.AppConstants
import com.martinszuc.templateapp.utils.AppConstants.THEME_DARK
import com.martinszuc.templateapp.utils.AppConstants.THEME_LIGHT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

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

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    application: Application // Use Application instead of Context
) : AndroidViewModel(application) {

    private val THEMEKEY = stringPreferencesKey(AppConstants.THEME_PREFERENCE)

    val themePreference: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[THEMEKEY] ?: AppConstants.THEME_SYSTEM_DEFAULT
        }

    fun changeTheme(theme: String) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[THEMEKEY] = theme
            }
            Log.d("SettingsViewModel", "Theme changed to: $theme")
            applyTheme(theme)
        }
    }

    private fun applyTheme(theme: String) {
        when (theme) {
            THEME_LIGHT -> {
                Log.d("SettingsViewModel", "Applying Light Theme")
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            THEME_DARK -> {
                Log.d("SettingsViewModel", "Applying Dark Theme")
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            AppConstants.THEME_SYSTEM_DEFAULT -> {
                Log.d("SettingsViewModel", "Applying System Default Theme")
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }
}
