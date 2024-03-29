package com.blue.history.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.blue.history.HistoryScreen

const val historyRoute = "History"
fun NavController.navigateToHistory(navOptions: NavOptions){
    this.navigate(historyRoute, navOptions)
}

fun NavGraphBuilder.historyScreen(onClick:()-> Unit){
    composable(route = historyRoute){
        HistoryScreen()
    }
}