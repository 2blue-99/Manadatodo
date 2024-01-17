package com.blue.history

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.blue.designsystem.component.TodoComponent
import com.blue.history.state.HistoryUiState
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    historyViewModel: HistoryViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean =
                utcTimeMillis <= System.currentTimeMillis()
        })
        val selectedDate = datePickerState.selectedDateMillis?.let { Util.convertMillisToDate(it) } ?: "${LocalDate.now()}"
        val historyUiState by historyViewModel.historyUiState.collectAsStateWithLifecycle()

        historyViewModel.getSelectedData(selectedDate)

        Column(
            horizontalAlignment = Alignment.Start
        ) {

            DatePicker(state = datePickerState)

            HistoryContentWithStatus(
                historyUiState = historyUiState,
                selectedDate = selectedDate
            )
        }
    }
}

@Composable
fun HistoryContentWithStatus(
    historyUiState: HistoryUiState,
    selectedDate: String?,
) {
    when (historyUiState) {
        is HistoryUiState.Loading -> {}
        is HistoryUiState.Error -> {}
        is HistoryUiState.Success -> {
            HistoryContent(
                uiState = historyUiState,
                selectedDate = selectedDate
            )
        }
    }
}

@Composable
fun HistoryContent(
    uiState: HistoryUiState.Success,
    selectedDate: String?
) {
    LazyColumn(
        verticalArrangement = Arrangement.Top,
    ) {
        item { Text(text = selectedDate ?: "", fontSize = 25.sp, modifier = Modifier.padding(start = 10.dp)) }

        item { Text(text = "${uiState.doneCnt}/${uiState.totalCnt}", Modifier.padding(start = 10.dp)) }

        items(uiState.todoList, key = {it.id}){
            TodoComponent(todo = it, onClickCheckBox = {}, onClick = {})
        }
    }
}




