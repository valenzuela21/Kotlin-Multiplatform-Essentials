package com.kmp.itunessearchapi.network

import com.kmp.itunessearchapi.model.ApiResponse
import com.kmp.itunessearchapi.model.ItunesModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.encodeURLParameter

class ItunesApi(private val client: HttpClient) {
    suspend fun search(term:String): List<ItunesModel>{
        val search = term.encodeURLParameter()
        val url = "https://itunes.apple.com/search?term=$search"
        val response = client.get(url).body<ApiResponse>()
        return response.results
    }
}