package com.blue.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.domain.auth.GetAuthUseCase
import com.blue.domain.auth.GetTokenUseCase
import com.blue.domain.datastore.GetDataStoreFlowUseCase
import com.blue.domain.datastore.GetDataStoreUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getAuthUseCase: GetAuthUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val getDataStoreFlowUseCase: GetDataStoreFlowUseCase,
    private val getDataStoreUpdateUseCase: GetDataStoreUpdateUseCase
): ViewModel() {
    private var _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess
    fun getAuth(): ComposeAuth = getAuthUseCase()
    fun isLogin(){
        viewModelScope.launch {
            getDataStoreFlowUseCase().collect{
                if(it.isNotBlank())
                    _isSuccess.value = true
            }
        }
    }
    private fun updateToken(){
        viewModelScope.launch {
            getTokenUseCase()?.let {
                getDataStoreUpdateUseCase.invoke(it)
            }
        }
    }
    fun checkGoogleLoginStatus(result: NativeSignInResult){
        when(result){
            is NativeSignInResult.Success -> {
                _isSuccess.value = true
                updateToken()
            }
            is NativeSignInResult.ClosedByUser -> {}
            is NativeSignInResult.Error -> {}
            is NativeSignInResult.NetworkError -> {}
        }
    }
}