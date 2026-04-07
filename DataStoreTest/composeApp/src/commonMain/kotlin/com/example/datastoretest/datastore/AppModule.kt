package com.example.datastoretest.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

object AppModule {

    private val dataStore: DataStore<Preferences> by lazy {
        createDataStore { dataStoreFileName() }
    }

    val preferenceRepository: PreferencesRepository by lazy {
        PreferencesRepository(dataStore)
    }
}