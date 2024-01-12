package com.blue.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blue.domain.GetAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getAuthUseCase: GetAuthUseCase
): ViewModel() {

    private var _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    fun getAuth(): ComposeAuth = getAuthUseCase.invoke()

    fun checkGoogleLoginStatus(result: NativeSignInResult){
        when(result){
            is NativeSignInResult.Success -> {
                Log.e("TAG", "checkGoogleLoginStatue: Success $result", )
                _isSuccess.postValue(true)
            }
            is NativeSignInResult.ClosedByUser -> {
                Log.e("TAG", "checkGoogleLoginStatue: ClosedByUser", )
            }
            is NativeSignInResult.Error -> {
                Log.e("TAG", "checkGoogleLoginStatue: Error ${result.message}")
            }
            is NativeSignInResult.NetworkError -> {
                Log.e("TAG", "checkGoogleLoginStatue: NetworkError", )
            }
        }
    }
}