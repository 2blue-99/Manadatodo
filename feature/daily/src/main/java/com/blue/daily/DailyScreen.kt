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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.blue.daily.state.BottomSheetUiState
import com.blue.daily.state.DailyUiState
import com.blue.daily.state.TodoUiState
import com.blue.designsystem.component.TodoAddButton
import com.blue.designsystem.component.TodoComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyScreen(
    dailyViewModel: DailyViewModel = hiltViewModel(),
) {
//    val datas by viewModel.getAllData().collectAsStateWithLifecycle(emptyList())
    val dailyUiState by dailyViewModel.dailyUiState.collectAsStateWithLifecycle()
    val bottomSheetUiState by dailyViewModel.bottomSheetUiState.collectAsStateWithLifecycle()

//    val bottomSheetState = rememberModalBottomSheetState()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        floatingActionButton = {
            TodoAddButton(onClick = { dailyViewModel.changeBottomSheet(true)})
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
                uiData = dailyUiState,
                bottomSheetUiState = bottomSheetUiState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyContent(
    uiData: DailyUiState.Success,
    bottomSheetUiState: BottomSheetUiState,
    dailyViewModel: DailyViewModel = hiltViewModel()
) {
    val bottomSheetState = rememberModalBottomSheetState()

    if(bottomSheetUiState is BottomSheetUiState.Up){
        AddBottomSheet(
            todo = bottomSheetUiState.todoUiState.todo,
            sheetState = bottomSheetState,
            insertData = dailyViewModel::insertData,
            deleteData = dailyViewModel::deleteData,
            onDismiss = { dailyViewModel.changeBottomSheet(false) }
        )
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        item {
            Text(
                text = uiData.today.split("T").first(),
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 25.sp,
                modifier = Modifier.padding(30.dp)
            )
        }

        item {
            Text(
                text = "${uiData.doneCnt}/${uiData.totalCnt}",
                modifier = Modifier.padding(start = 30.dp)
            )
        }

        items(uiData.todoList, key = { it.id }) {
            TodoComponent(
                todo = it,
                onClickCheckBox = { dailyViewModel.changeCheckBox(it.id, !it.isDone) },
                onClick = {
                    dailyViewModel.changeBottomSheet(true, TodoUiState.Exist(it))
                }
            )
        }
    }
}