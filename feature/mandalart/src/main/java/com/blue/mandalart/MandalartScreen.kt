package com.blue.mandalart

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.blue.designsystem.component.CountButton
import com.blue.mandalart.uistate.MandalartUiState

@Composable
fun MandalartScreen(
    mandalartViewModel: MandalartViewModel = hiltViewModel()
) {

    val mandalartUiState by mandalartViewModel.getAllMandalart.collectAsStateWithLifecycle()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            MandalartContentWithStatus(mandalartUiState)
        }
    }
}

@Composable
fun MandalartContentWithStatus(
    mandalartUiState: MandalartUiState,
) {
    when (mandalartUiState) {
        is MandalartUiState.Loading -> {
            UninitializedMandalart()
        }

        is MandalartUiState.Error -> {}
        is MandalartUiState.Success -> {
            InitializedMandalartContent(mandalartUiState)
        }
    }
}

@Composable
fun UninitializedMandalart(
    mandalartViewModel: MandalartViewModel = hiltViewModel()
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Text("만다라트 초기화 전") }

        item { Spacer(modifier = Modifier.height(20.dp)) }

        item {
            Button(
                colors = ButtonDefaults.buttonColors(Color.Gray),
                shape = RectangleShape,
                modifier = Modifier.size(height = 300.dp, width = 300.dp),
                onClick = { mandalartViewModel.initMandalart() }
            ) { Text(text = "생성하기") }
        }
    }
}

@Composable
fun InitializedMandalartContent(
    uiData: MandalartUiState.Success,
    mandalartViewModel: MandalartViewModel = hiltViewModel()
) {
    Column {
        Text("만다라트 초기화 후")

        Spacer(modifier = Modifier.height(20.dp))

        Mandalart(uiData)

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "총합 : ${uiData.sum}")

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { mandalartViewModel.deleteAllMandalart() }) {
            Text(text = "초기화 하기")
        }
//        item { Text("만다라트 초기화 후") }
//
//        item { Spacer(modifier = Modifier.height(20.dp)) }
//
//        item { Mandalart(mandalartUiState) }
    }
}

@Composable
fun Mandalart(
    uiData: MandalartUiState.Success,
//    mandalartViewModel: MandalartViewModel = hiltViewModel()
) {
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        columns = GridCells.Fixed(3),
    ) {
        Log.e("TAG", "Mandalart: ${uiData}")
        items(uiData.sum) {
            val data = uiData.mandalart[it]
            CountButton(
                cnt = data.cnt,
                onClick = { /*mandalartViewModel.updateMandalart(data.cnt, data.cnt)*/ })
        }
    }
}