package com.blue.mandatodo.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ManadaApp(
    appState: MandaAppState = RememberMandaState()
){
    Button(onClick = { /*TODO*/ }) {
        Text(text = "기모찌")
    }
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        bottomBar = {
//            BottomBar(
//
//            )
//        }
//    ) {
//
//    }
}

@Composable
fun BottomBar(

){

}