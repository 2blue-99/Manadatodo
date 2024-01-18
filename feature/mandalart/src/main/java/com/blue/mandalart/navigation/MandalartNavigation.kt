package com.blue.mandalart.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.blue.mandalart.MandalartScreen

const val mandalartRoute = "Mandalart"
fun NavController.navigateToMandalart(navOptions: NavOptions){
    this.navigate(mandalartRoute, navOptions)
}

fun NavGraphBuilder.mandalartScreen(onClick:()-> Unit){
    composable(route = mandalartRoute){
        MandalartScreen()
    }
}