package com.blue.mandatodo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.blue.daily.navigation.navigateToDaily
import com.blue.history.navigation.navigateToHistory
import com.blue.mandalart.navigation.navigateToMandalart
import com.blue.mandatodo.navigation.Destination

@Composable
fun RememberMandaState(
    navController: NavHostController = rememberNavController()
): MandaAppState {
    return remember(navController){
        MandaAppState(navController)
    }
}

@Stable
class MandaAppState(
    val navController: NavHostController
){
    val destinations: List<Destination> = Destination.entries

    val currentLocation: NavDestination?
        @Composable get()= navController.currentBackStackEntryAsState().value?.destination

    fun navigationToDestination(name: String){
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            // 백스택에 얘만 있게 하는 역할?
            launchSingleTop = true
            // 상태 저장
            restoreState = true
        }
        when(name){
            Destination.DAILY.titleTextId -> navController.navigateToDaily (navOptions)
            Destination.HISTORY.titleTextId -> navController.navigateToHistory(navOptions)
            Destination.MANDALART.titleTextId-> navController.navigateToMandalart(navOptions)
        }
    }
}