package com.kmp.palette.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.palette.copyToClipboard
import com.kmp.palette.models.ColorModel
import com.kmp.palette.usesCases.colors.DeleteByIdUseCase
import com.kmp.palette.usesCases.colors.DeleteColorUseCase
import com.kmp.palette.usesCases.colors.GetColorsUseCase
import com.kmp.palette.usesCases.colors.InsertColorUseCase
import com.kmp.palette.usesCases.colors.UpdateColorUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class ColorViewModel(
    private val insertColorUseCase: InsertColorUseCase,
    private val getColorsUseCase: GetColorsUseCase,
    private val updateColorUseCase: UpdateColorUseCase,
    private val deleteColorUseCase: DeleteColorUseCase,
    private val deleteByIdUseCase: DeleteByIdUseCase
): ViewModel() {

    private val _colors = MutableStateFlow<List<ColorModel>>(emptyList()) // viewModel
    val colors: StateFlow<List<ColorModel>> = _colors // views

    fun insertColor(idPalette:Int){
        viewModelScope.launch { insertColorUseCase(idPalette) }
    }
    fun getColors(idPalette:Int): Flow<List<ColorModel>?>{
        return getColorsUseCase(idPalette)
    }

    fun editColor(colorItem: ColorModel, r:Int,g:Int,b:Int){
        viewModelScope.launch { updateColorUseCase(colorItem,r,g,b) }
    }

    fun deleteColor(colorItem: ColorModel){
        viewModelScope.launch { deleteColorUseCase(colorItem) }
    }

    fun deleteColorById(idPalette: Int){
        viewModelScope.launch { deleteByIdUseCase(idPalette) }
    }

    private fun getColorsCopy(idPalette: Int){
        viewModelScope.launch {
            getColorsUseCase(idPalette).collect { list ->
                _colors.value = list ?: emptyList()
            }
        }
    }

    fun copyAll(idPalette: Int) {
        getColorsCopy(idPalette)
        val hexString = _colors.value.joinToString(separator = "\n") { it.hex }
        copyToClipboard(hexString)
    }










//    private var id = 1
//
//    fun generateColor(){
//        val r = Random.nextInt(256)
//        val g = Random.nextInt(256)
//        val b = Random.nextInt(256)
//
//        val hex = ColorModel.rgbToHex(r,g,b)
//        val rgb = "RGB($r, $g, $b)"
//
//        val newColor = ColorModel(id++,r,g,b,hex,rgb)
//        _colors.value = _colors.value + newColor
//    }
//
//    fun removeColorById(id:Int){
//        _colors.value = _colors.value.filter { it.id != id }
//    }
//
//    fun editColor(id:Int,r:Int,g:Int,b:Int){
//        val hex = ColorModel.rgbToHex(r,g,b)
//        val rgb = "RGB($r, $g, $b)"
//
//        _colors.value = _colors.value.map { color ->
//            if (color.id == id){
//                color.copy(id,r,g,b,hex,rgb)
//            }else{
//                color
//            }
//        }
//
//    }
//
//    fun reset(){
//        _colors.value = emptyList()
//    }
//
//    fun copyAll(){
//        val hexstring = _colors.value.joinToString(separator = "\n") { it.hex }
//        copyToClipboard(hexstring)
//    }



}