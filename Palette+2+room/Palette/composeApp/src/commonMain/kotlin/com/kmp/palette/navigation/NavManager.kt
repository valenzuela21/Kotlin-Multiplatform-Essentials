package com.kmp.palette.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kmp.palette.views.DetailColorsView
import com.kmp.palette.views.HomeView
import com.kmp.palette.views.PaletteView

@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home){
        composable<Home> {
            HomeView(navController)
        }
        composable<Palette> { item ->
            val palette = item.toRoute<Palette>()
            PaletteView(navController, id = palette.id, name = palette.name)
        }
        composable<DetailColor> { item ->
            val color = item.toRoute<DetailColor>()
            DetailColorsView(navController, color)
        }

    }
}