package com.blue.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import okhttp3.internal.wait


@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    onClick:() -> Unit
){
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.DarkGray),
        onClick = { onClick() },
        shape = RoundedCornerShape(5.dp),
        modifier = modifier
            .size(250.dp, 50.dp)
            .background(Color.White),
    ) {
        Text(
            color = Color.DarkGray,
            text = "Sign in with Google"
        )
    }
}


@Preview
@Composable
fun Preview(){
    LoginButton {

    }
}