package com.kmp.palette.di

import com.kmp.palette.repositories.ColorRepository
import com.kmp.palette.repositories.PaletteRepository
import com.kmp.palette.room.CreateDatabase
import com.kmp.palette.room.RoomDb
import com.kmp.palette.usesCases.colors.DeleteByIdUseCase
import com.kmp.palette.usesCases.colors.DeleteColorUseCase
import com.kmp.palette.usesCases.colors.GetColorsUseCase
import com.kmp.palette.usesCases.colors.InsertColorUseCase
import com.kmp.palette.usesCases.colors.UpdateColorUseCase
import com.kmp.palette.usesCases.palette.DeletePaletteUseCase
import com.kmp.palette.usesCases.palette.GetPaletteUseCase
import com.kmp.palette.usesCases.palette.InsertPaletteUseCase
import com.kmp.palette.usesCases.palette.UpdatePaletteUseCase
import com.kmp.palette.viewModels.ColorViewModel
import com.kmp.palette.viewModels.PaletteViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    single <RoomDb> { CreateDatabase(get()).getDatabase() }

    //Repositories
    singleOf(::PaletteRepository)
    singleOf(::ColorRepository)

    //uses cases Palette
    singleOf(::InsertPaletteUseCase)
    singleOf(::UpdatePaletteUseCase)
    singleOf(::GetPaletteUseCase)
    singleOf(::DeletePaletteUseCase)

    //uses cases Colors
    singleOf(::InsertColorUseCase)
    singleOf(::GetColorsUseCase)
    singleOf(::UpdateColorUseCase)
    singleOf(::DeleteColorUseCase)
    singleOf(::DeleteByIdUseCase)

    //viewModels
    viewModelOf(::PaletteViewModel)
    viewModelOf(::ColorViewModel)
}