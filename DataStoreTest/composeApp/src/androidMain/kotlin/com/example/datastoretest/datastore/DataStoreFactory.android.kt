package com.example.datastoretest.datastore

import android.content.Context

private lateinit var appContext: Context

fun initDataStoreContext(context: Context){
    appContext = context.applicationContext
}

actual fun dataStoreFileName(): String {
    return appContext.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
}