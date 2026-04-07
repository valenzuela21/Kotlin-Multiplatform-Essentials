package com.kmp.itunessearchapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kmp.itunessearchapi.views.DetailView
import com.kmp.itunessearchapi.views.HomeView
import com.kmp.itunessearchapi.views.ResultsView

@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = Home){
        composable<Home> {
            HomeView(navController)
        }
        composable<Results> { item ->
            val result = item.toRoute<Results>()
            ResultsView(navController, result.search)
        }
        composable<Detail> { item ->
            val detail = item.toRoute<Detail>()
            DetailView(navController, detail)
        }
    }
}