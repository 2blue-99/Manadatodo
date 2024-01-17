package com.blue.mandatodo.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import com.blue.designsystem.component.MandaNavigationBarItem
import com.blue.login.LoginScreen
import com.blue.login.LoginViewModel
import com.blue.mandatodo.navigation.Destination
import com.blue.mandatodo.navigation.MandaNavHost
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import kotlinx.coroutines.flow.collect

@Composable
fun ManadaApp(
    navController: MandaAppState = RememberMandaState(),
    viewModel: LoginViewModel = hiltViewModel(),
){
    val token = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel.isSuccess.asFlow().collect{
            if(it) token.value = true
        }
    }
    viewModel.isLogin()
    if(token.value == false)
        LoginScreen(viewModel = viewModel){ token.value = true }
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