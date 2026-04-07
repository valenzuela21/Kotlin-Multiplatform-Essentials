package com.kmp.palette.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home
@Serializable
data class Palette(val id:Int, val name:String)

@Serializable
data class DetailColor(val id:Int, val name:String)