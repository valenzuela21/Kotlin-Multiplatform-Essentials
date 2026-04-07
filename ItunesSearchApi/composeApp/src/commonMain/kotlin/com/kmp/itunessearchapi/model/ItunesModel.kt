package com.kmp.itunessearchapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItunesModel(
    @SerialName("trackName") val name: String? = null,
    @SerialName("artworkUrl100") val image: String? = null,
    @SerialName("longDescription") val desc: String? = null,
    val kind: String? = null
)

@Serializable
data class ApiResponse(
    val results: List<ItunesModel> = emptyList()
)
