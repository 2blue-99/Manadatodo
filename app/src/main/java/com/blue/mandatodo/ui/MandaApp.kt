package com.blue.mandatodo.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import com.blue.designsystem.component.MandaNavigationBarItem
import com.blue.login.LoginScreen
import com.blue.mandatodo.navigation.Destination
import com.blue.mandatodo.navigation.MandaNavHost

@Composable
fun ManadaApp(
    navController: MandaAppState = RememberMandaState()
){
    val token = remember { mutableStateOf("") }
    if(token.value == "")
        LoginScreen{
            token.value = "success"
        }
    else{
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomBar(
                    destination = navController.destinations,
                    navigate = navController::navigationToDestination,
                    checkCurrentLocation = navController.currentLocation
                )
            }
        ) { padding ->
            MandaNavHost(
                modifier = Modifier.padding(padding),
                appState = navController
            )

        }
    }


}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    destination: List<Destination>,
    navigate: (String) -> Unit,
    checkCurrentLocation: NavDestination?
){
    NavigationBar(
        modifier = modifier
    ){
        destination.forEach { destination ->
            val isSelected = checkCurrentLocation?.route == destination.titleTextId
            MandaNavigationBarItem(
                selected = isSelected,
                onClick = { navigate(destination.titleTextId) },
                icon = if(isSelected) destination.selectedIcon else destination.unSelectedIcon,
                modifier = modifier,
                label = destination.titleTextId,
            )
        }
    }
}