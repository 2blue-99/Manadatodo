package com.blue.daily

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.blue.designsystem.component.TodoAddButton
import com.blue.designsystem.component.TodoComponent
import com.blue.model.Todo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyScreen(
    viewModel: DailyViewModel = hiltViewModel(),
) {
//    val datas by viewModel.getAllData().collectAsStateWithLifecycle(emptyList())

    val dailyUiState by viewModel.dailyUiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }

    if (isSheetOpen)
        AddBottomSheet(
            sheetState = sheetState,
            uiState = dailyUiState,
            insertData = viewModel::insertData,
            deleteData = viewModel::deleteData,
            onDismiss = { isSheetOpen = false }
        )

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        floatingActionButton = {
            TodoAddButton(onClick = { isSheetOpen = true })
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            DailyContentWithStatus(dailyUiState = dailyUiState)
        }
    }
}

@Composable
fun DailyContentWithStatus(
    dailyUiState: DailyUiState
){
    when(val uiState = dailyUiState){
       is DailyUiState.Loading -> {}
       is DailyUiState.Error -> {}
       is DailyUiState.Success -> {
           DailyContent(
               list = uiState.todoList,
               totalCnt = uiState.totalCnt,
               doneCnt = uiState.doneCnt,
           )
       }
    }
}

@Composable
fun DailyContent(
    list: List<Todo>,
    totalCnt: Int,
    doneCnt: Int,
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        item {
            Text(
                text = "오늘 날짜",
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 40.sp,
                modifier = Modifier.padding(30.dp)
            )
        }

        item { Text(text = "$doneCnt/$totalCnt", modifier = Modifier.padding(start = 30.dp)) }

        items(list, key = { it.id }) {
            TodoComponent(
                title = it.title,
                content = it.content,
                isChecked = it.isDone,
                onClick = {
                    isSheetOpen = true
                }
            )
        }
    }
}