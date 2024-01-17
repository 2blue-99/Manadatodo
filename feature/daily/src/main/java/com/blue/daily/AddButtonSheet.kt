package com.blue.daily

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.blue.database.model.TodoEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBottomSheet(
    id: Int = -1,
    sheetState: SheetState,
    uiState: DailyUiState,
    title: String? = null,
    content: String? = null,
    insertData: (TodoEntity) -> Unit,
    deleteData: (Int) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                var titleTxt by remember { mutableStateOf("") }
                var contentTxt by remember { mutableStateOf("") }
                if(!title.isNullOrBlank()) titleTxt = title
                if(!content.isNullOrBlank()) contentTxt = content

                OutlinedTextField(
                    value = titleTxt,
                    onValueChange = { titleTxt = it },
                    label = { Text("Title") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp)
                )
                OutlinedTextField(
                    value = contentTxt,
                    onValueChange = { contentTxt = it },
                    label = { Text("Content") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp)
                )
                Row{
                    Button(
                        onClick = {
                            insertData(
                                TodoEntity(id = 0, date = "System.currentTimeMillis()",title = titleTxt, content = contentTxt, isDone = false)
                            )
                            onDismiss()
                        }
                    ) {
                        Text(text = "추가하기")
                    }

                    Button(
                        onClick = {
                            if(id!=-1) deleteData(id)
                            onDismiss()
                        }
                    ) {
                        Text(text = "삭제하기")
                    }
                }
            }

        }
    }
}

fun main(){
    Log.e("TAG", "main: ${System.currentTimeMillis()}")
}