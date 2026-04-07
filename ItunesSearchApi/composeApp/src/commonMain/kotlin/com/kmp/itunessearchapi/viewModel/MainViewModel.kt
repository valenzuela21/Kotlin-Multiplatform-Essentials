package com.kmp.itunessearchapi.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.itunessearchapi.model.ItunesModel
import com.kmp.itunessearchapi.useCase.GetItunesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getItunesUseCase: GetItunesUseCase
): ViewModel() {
    private val _itunesList = MutableStateFlow<List<ItunesModel>>(emptyList())
    val itunesList : StateFlow<List<ItunesModel>> = _itunesList

    private val _filteredList = MutableStateFlow<List<ItunesModel>>(emptyList())
    val filteredList : StateFlow<List<ItunesModel>> = _filteredList

    var searchQuery by mutableStateOf("")
        private set

    fun onSearchValue(query:String){
        searchQuery = query

        _filteredList.value = if(query.isEmpty()){
            _itunesList.value
        }else{
            _itunesList.value.filter {
                it.name?.contains(query, ignoreCase = true) ?: false
            }
        }
    }

    fun getSearch(search:String){
        viewModelScope.launch {
            _itunesList.value = getItunesUseCase(search)
            _filteredList.value = _itunesList.value
        }
    }
}