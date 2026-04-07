package com.kmp.palette.viewModels

import androidx.lifecycle.ViewModel
import com.kmp.palette.copyToClipboard
import com.kmp.palette.models.ColorModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class ColorViewModel: ViewModel() {

    private val _colors = MutableStateFlow<List<ColorModel>>(emptyList()) // viewModel
    val colors: StateFlow<List<ColorModel>> = _colors // views

    private var id = 1

    fun generateColor(){
        val r = Random.nextInt(256)
        val g = Random.nextInt(256)
        val b = Random.nextInt(256)

        val hex = ColorModel.rgbToHex(r,g,b)
        val rgb = "RGB($r, $g, $b)"

        val newColor = ColorModel(id++,r,g,b,hex,rgb)
        _colors.value = _colors.value + newColor
    }

    fun removeColorById(id:Int){
        _colors.value = _colors.value.filter { it.id != id }
    }

    fun editColor(id:Int,r:Int,g:Int,b:Int){
        val hex = ColorModel.rgbToHex(r,g,b)
        val rgb = "RGB($r, $g, $b)"

        _colors.value = _colors.value.map { color ->
            if (color.id == id){
                color.copy(id,r,g,b,hex,rgb)
            }else{
                color
            }
        }

    }

    fun reset(){
        _colors.value = emptyList()
    }

    fun copyAll(){
        val hexstring = _colors.value.joinToString(separator = "\n") { it.hex }
        copyToClipboard(hexstring)
    }



}