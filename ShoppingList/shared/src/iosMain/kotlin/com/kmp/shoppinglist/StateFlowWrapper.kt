package com.kmp.shoppinglist

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StateFlowWrapper<T>(private val flow: StateFlow<T>) {
    fun watch(callback: (T) -> Unit ){
        CoroutineScope(Dispatchers.Main).launch {
            flow.collect { callback(it) }
        }
    }
}