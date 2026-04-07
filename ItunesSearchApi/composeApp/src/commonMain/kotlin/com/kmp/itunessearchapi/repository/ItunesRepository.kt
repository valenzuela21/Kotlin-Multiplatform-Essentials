package com.kmp.itunessearchapi.repository

import com.kmp.itunessearchapi.model.ItunesModel
import com.kmp.itunessearchapi.network.ItunesApi

class ItunesRepository(private val itunes: ItunesApi) {
    suspend fun search(search:String): List<ItunesModel> = itunes.search(search)
}