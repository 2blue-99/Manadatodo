package com.blue.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TodoComponent(
    title: String,
    content: String,
    isChecked: Boolean,
    onCheck: () -> Unit = {},
    onDelete: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.LightGray
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Checkbox(
                checked = isChecked, onCheckedChange = {onCheck()}
            )
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = title, style = MaterialTheme.typography.headlineSmall)

                Text(text = "- $content")
            }
            IconButton(
                onClick = { onDelete() },
            ) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "")
            }
        }
    }
}

@Preview
@Composable
fun test() {
    TodoComponent(title = "", content = "", isChecked = true)
}