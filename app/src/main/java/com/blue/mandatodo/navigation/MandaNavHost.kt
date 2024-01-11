package com.blue.mandatodo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.blue.daily.DailyScreen
import com.blue.daily.navigation.dailyScreen
import com.blue.history.HistoryScreen
import com.blue.history.navigation.historyScreen
import com.blue.mandalart.navigation.mandalartScreen
import com.blue.mandatodo.ui.MandaAppState

@Composable
fun MandaNavHost(
    modifier: Modifier,
    appState: MandaAppState
){
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = Destination.MANDALART.titleTextId,
        modifier = modifier
    ){
        mandalartScreen(onClick = {
//            navController.popBackStack()
            navController.navigate(Destination.MANDALART.titleTextId)
        })
        historyScreen(onClick = {
            navController.navigate(Destination.HISTORY.titleTextId)
        })
        dailyScreen(onClick = {
            navController.navigate(Destination.DAILY.titleTextId)
        })
    }
}