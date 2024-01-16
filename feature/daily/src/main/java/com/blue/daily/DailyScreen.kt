package com.blue.daily

import androidx.compose.foundation.layout.Arrangement
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
    val datas by viewModel.getAllData().collectAsStateWithLifecycle(emptyList())

    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }

    if (isSheetOpen)
        AddBottomSheet(sheetState = sheetState, insertData = viewModel::insertData, deleteData = viewModel::deleteData) { isSheetOpen = false }

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        floatingActionButton = {
            TodoAddButton(onClick = { isSheetOpen = true })
        }
    ) { padding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(padding),
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

            item { Text(text = "0/3", modifier = Modifier.padding(start = 30.dp)) }

            items(datas, key = { it.id }) {
                TodoComponent(
                    title = it.title,
                    content = it.content,
                    isChecked = it.isChecked,
                )
            }
        }
    }
}