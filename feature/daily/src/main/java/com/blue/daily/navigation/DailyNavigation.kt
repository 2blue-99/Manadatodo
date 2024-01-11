package com.blue.daily.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.blue.daily.DailyScreen

const val dailyRoute = "Daily"
fun NavController.navigateToDaily(navOptions: NavOptions){
    this.navigate(dailyRoute, navOptions)
}

fun NavGraphBuilder.dailyScreen(onClick:()-> Unit){
    composable(route = dailyRoute){
        DailyScreen()
    }
}