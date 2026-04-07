package com.kmp.itunessearchapi.di

import com.kmp.itunessearchapi.network.ItunesApi
import com.kmp.itunessearchapi.network.NetworkClient
import com.kmp.itunessearchapi.repository.ItunesRepository
import com.kmp.itunessearchapi.useCase.GetItunesUseCase
import com.kmp.itunessearchapi.viewModel.MainViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    single { NetworkClient().httpClient }
    single { ItunesApi(get()) }
    singleOf(::ItunesRepository)
    singleOf(::GetItunesUseCase)
    viewModelOf(::MainViewModel)
}