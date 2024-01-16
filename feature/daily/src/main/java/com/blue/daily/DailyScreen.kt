package com.blue.daily

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DailyScreen(
    viewModel: DailyViewModel = hiltViewModel()
){
    val datas by viewModel.getAllData().collectAsState(emptyList())
    Column(
    ) {
        Text(
            text = "오늘 날짜",
            style = MaterialTheme.typography.headlineSmall,
        )

        Text(
            text = "0/3",
        )


        LazyColumn(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ){
            items(datas, key = null){

            }
        }
    }
}