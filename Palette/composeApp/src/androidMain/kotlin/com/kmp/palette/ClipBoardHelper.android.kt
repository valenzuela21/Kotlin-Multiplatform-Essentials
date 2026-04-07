package com.kmp.palette

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

private var appContext: Context? = null

fun initClipboard(context: Context){
    appContext = context.applicationContext
}

actual fun copyToClipboard(text: String) {
    val context = appContext ?: return
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("copied_text", text)
    clipboard.setPrimaryClip(clip)
}