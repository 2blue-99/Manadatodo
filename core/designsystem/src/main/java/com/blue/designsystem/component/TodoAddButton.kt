package com.blue.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TodoAddButton(
    onClick: () -> Unit,
){
    FloatingActionButton(
        containerColor = Color.Blue,
        modifier = Modifier.border(1.dp,Color.Blue, CircleShape),
        shape = CircleShape,
        onClick = { onClick() }
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = Color.White, modifier = Modifier.size(60.dp))
    }
}

@Composable
@Preview
fun TodoAddBtnTest(){
    TodoAddButton {

    }
}