package com.blue.login

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
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onSuccess: () -> Unit
) {
    //TODO 왜 LaunchEffect?
    LaunchedEffect(Unit) {
        viewModel.isSuccess.asFlow().collect {
            onSuccess()
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