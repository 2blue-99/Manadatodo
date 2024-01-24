package com.blue.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import com.blue.designsystem.component.LoginButton
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onSuccess: () -> Unit
) {
    val context = LocalContext.current
    //TODO 왜 LaunchEffect?
    LaunchedEffect(Unit) {
        viewModel.isSuccess.asFlow().collect {
            Log.e("TAG", "LoginScreen: $it",)
            if(it) {
                Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show()
                onSuccess()
            }else{
                Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val authState = viewModel.getAuth().rememberSignInWithGoogle(
        onResult = { result ->
            viewModel.checkGoogleLoginStatus(result)
        },
        fallback = {}
    )

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginButton {
            authState.startFlow()
        }
    }

}