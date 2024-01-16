package com.blue.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TodoComponent(
    title: String,
    content: String,
    isChecked: Boolean,
    onClickCheck: () -> Unit = {},
    onClickDelete: (Int) -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Row {
            Checkbox(
                checked = isChecked, onCheckedChange = {onClickCheck()}
            )
            Column {
                Text(text = title, style = MaterialTheme.typography.headlineSmall)

                Text(text = "- $content")
            }
        }
    }
}

@Preview
@Composable
fun test() {
    TodoComponent(title = "", content = "", isChecked = true)
}