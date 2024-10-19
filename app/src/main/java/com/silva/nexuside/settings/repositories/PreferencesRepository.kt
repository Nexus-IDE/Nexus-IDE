package com.silva.nexuside.settings.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.silva.nexuside.core.settings.DefaultPrefs
import kotlinx.coroutines.flow.map

private val uiModePreference = intPreferencesKey("app_ui_mode")

class PreferencesRepository(private val dataStore: DataStore<Preferences>) {

    val uiMode = dataStore.data.map { it[uiModePreference] ?: DefaultPrefs.UI_MODE }

    suspend fun changeUiMode(value: Int) {
        dataStore.edit { preferences -> preferences[uiModePreference] = value }
    }
}
