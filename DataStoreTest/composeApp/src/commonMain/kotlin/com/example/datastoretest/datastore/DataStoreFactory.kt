package com.example.datastoretest.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

internal const val DATA_STORE_FILE_NAME = "user_preferences.preferences_pb"

expect fun dataStoreFileName(): String

fun createDataStore(producePath: () -> String ): DataStore<Preferences>{
    return PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() }
    )
}