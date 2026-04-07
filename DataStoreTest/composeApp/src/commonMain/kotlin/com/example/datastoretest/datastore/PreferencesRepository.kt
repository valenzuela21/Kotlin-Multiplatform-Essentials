package com.example.datastoretest.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesRepository(private val dataStore: DataStore<Preferences>) {

    companion object {
        val USER_NAME_KEY = stringPreferencesKey("user_name")

        val VISIT_COUNT_KEY = intPreferencesKey("visit_count")

        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode_enabled")
    }
    // Write operations (Suspend functions)

    suspend fun saveUserName(name:String){
        dataStore.edit { preference ->
            preference[USER_NAME_KEY] = name
        }
    }

    suspend fun incrementVisitCount() {
        dataStore.edit { preference ->
            val currentCount = preference[VISIT_COUNT_KEY] ?: 0
            preference[VISIT_COUNT_KEY] = currentCount + 1
        }
    }

    suspend fun setDarkMode(enabled: Boolean){
        dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = enabled
        }
    }

    // Read operations (Flows)

    val userName: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[USER_NAME_KEY] ?: "User"
        }
    val visitCount: Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[VISIT_COUNT_KEY] ?: 0
        }

    val isDarkModeEnabled: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[DARK_MODE_KEY] ?: false
        }

    // delete preference
    suspend fun clearAllPreferences(){
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

}