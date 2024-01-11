package com.blue.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.blue.login.LoginScreen

const val historyRoute = "Login"

fun NavController.navigateToLogin(navOptions: NavOptions){
    this.navigate(historyRoute, navOptions)
}

fun NavGraphBuilder.loginScreen(onClick:() -> Unit){
    composable(route = historyRoute){
        LoginScreen()
    }
}