package com.blue.designsystem.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CountButton(
    cnt: Int,
    onClick: (Int) -> Unit
){
    Button(
        modifier = Modifier.size(height = 60.dp, width = 60.dp),
        shape = RectangleShape,
        onClick = { onClick(cnt+1) },
        colors = ButtonDefaults.buttonColors(Color.Gray),
    ) {
        Text(text = "$cnt")
    }
}

@Composable
@Preview
fun CountBtnTest(){
    CountButton(cnt = 1, onClick = {})
}
