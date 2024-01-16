package com.blue.daily

import android.content.Context
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blue.database.model.TodoEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBottomSheet(
    sheetState: SheetState,
    insertData: (TodoEntity) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth().height(350.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(value = "Title", onValueChange = {}, modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp))
                OutlinedTextField(value = "Content", onValueChange = {}, modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp))



                Button(
                    onClick = {
                        insertData(
                            TodoEntity(
                                id = 0,
                                title = "hello",
                                content = "content~",
                                isChecked = true
                            )
                        )
                    }
                ) {
                    Text(text = "추가하기")
                }
            }

        }
    }
}