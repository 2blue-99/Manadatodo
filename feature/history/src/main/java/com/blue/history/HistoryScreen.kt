package com.blue.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    historyViewModel: HistoryViewModel = hiltViewModel()
){
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates{
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= System.currentTimeMillis()
            }
        })

        val selectedDate = datePickerState.selectedDateMillis?.let {
            Util.convertMillisToDate(it)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            DatePicker(state = datePickerState)

            Spacer(modifier = Modifier.height(15.dp))

            Text(text = "선택된 날")

            Text(
                text = selectedDate.toString(),
                color = Color.Red
            )
        }
    }
}


