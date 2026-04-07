package com.example.datastoretest.datastore

import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual fun dataStoreFileName(): String {
    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLsForDirectory(
        directory = NSDocumentDirectory,
        inDomains = NSUserDomainMask
    ).firstOrNull() as? NSURL

    return requireNotNull(
        documentDirectory?.path()?.let { "$it/$DATA_STORE_FILE_NAME" }
    ){
        "No se pudo obtener el directorio"
    }

}