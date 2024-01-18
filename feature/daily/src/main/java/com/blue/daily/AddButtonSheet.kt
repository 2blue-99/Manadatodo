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
import com.blue.model.Todo
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBottomSheet(
    todo: Todo,
    sheetState: SheetState,
    insertData: (Todo) -> Unit,
    deleteData: (Int) -> Unit,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    ) {

        Log.e("TAG", "AddBottomSheet: $todo", )

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
                var titleTxt by remember { mutableStateOf(todo.title) }
                var contentTxt by remember { mutableStateOf(todo.content) }
                Log.e("TAG", "AddBottomSheet: $titleTxt, $contentTxt", )
//                if (todo.id == -1) {
//                    titleTxt = todo.title
//                    contentTxt = todo.content
//                }

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
                Row {
                    Button(
                        onClick = {
                            insertData(
                                Todo(
                                    date = LocalDate.now().toString(),
                                    title = titleTxt,
                                    content = contentTxt,
                                    isDone = false,
                                    id = todo.id
                                )
                            )
                            onDismiss()
                        }
                    ) { Text(text = "추가하기") }

                    if (todo.id != 0)
                        Button(
                            onClick = {
                                deleteData(todo.id)
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