package com.kmp.itunessearchapi.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Results(val search: String)

@Serializable
data class Detail(
    val name: String?,
    val image: String?,
    val kind: String?,
    val desc: String?
)