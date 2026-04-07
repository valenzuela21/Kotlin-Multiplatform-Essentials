package com.kmp.itunessearchapi.useCase

import com.kmp.itunessearchapi.model.ItunesModel
import com.kmp.itunessearchapi.repository.ItunesRepository

class GetItunesUseCase(private val repository: ItunesRepository) {
    suspend operator fun invoke(search:String): List<ItunesModel> = repository.search(search)
}