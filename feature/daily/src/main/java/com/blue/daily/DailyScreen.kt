package com.blue.daily

import android.util.Log
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyScreen(
    viewModel: DailyViewModel = hiltViewModel(),
) {
//    val datas by viewModel.getAllData().collectAsStateWithLifecycle(emptyList())
    val dailyUiState by viewModel.dailyUiState.collectAsStateWithLifecycle()
    val bottomSheetUiState by viewModel.bottomSheetUiState.collectAsStateWithLifecycle()

//    val bottomSheetState = rememberModalBottomSheetState()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        floatingActionButton = {
            TodoAddButton(onClick = { viewModel.changeBottomSheet(true)})
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            DailyContentWithStatus(
                dailyUiState = dailyUiState,
                bottomSheetUiState = bottomSheetUiState
            )
        }
    }
}

@Composable
fun DailyContentWithStatus(
    dailyUiState: DailyUiState,
    bottomSheetUiState: BottomSheetUiState
) {
    when (dailyUiState) {
        is DailyUiState.Loading -> {}
        is DailyUiState.Error -> {}
        is DailyUiState.Success -> {
            DailyContent(
                uiState = dailyUiState,
                bottomSheetUiState = bottomSheetUiState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyContent(
    uiState: DailyUiState.Success,
    bottomSheetUiState: BottomSheetUiState,
    dailyViewModel: DailyViewModel = hiltViewModel()
) {
    val bottomSheetState = rememberModalBottomSheetState()

    if(bottomSheetUiState is BottomSheetUiState.Up){
        AddBottomSheet(
            todo = bottomSheetUiState.todoUiState.todo,
            insertData = dailyViewModel::insertData,
            deleteData = dailyViewModel::deleteData,
            onDismiss = { dailyViewModel.changeBottomSheet(false) },
            sheetState = bottomSheetState
        )
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        item {
            Text(
                text = uiState.today,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 25.sp,
                modifier = Modifier.padding(30.dp)
            )
        }

        item {
            Text(
                text = "${uiState.doneCnt}/${uiState.totalCnt}",
                modifier = Modifier.padding(start = 30.dp)
            )
        }

        items(uiState.todoList, key = { it.id }) {
            TodoComponent(
                title = it.title,
                content = it.content,
                isChecked = it.isDone,
                onClick = {
                    Log.e("TAG", "DailyContent: aa", )
                    dailyViewModel.changeBottomSheet(true, TodoUiState.Exist(it))
                }
            )
        }
    }
}