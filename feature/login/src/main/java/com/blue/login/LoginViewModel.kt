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
                else
                    _isSuccess.value = false
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
        Log.e("TAG", "checkGoogleLoginStatus: $result", )
        when(result){
            is NativeSignInResult.Success -> {
                _isSuccess.postValue(true)
                updateToken()
            }
            is NativeSignInResult.ClosedByUser -> {
                _isSuccess.postValue(false)
            }
            is NativeSignInResult.Error -> {
                _isSuccess.postValue(false)
            }
            is NativeSignInResult.NetworkError -> {
                _isSuccess.postValue(false)
            }
        }
    }
}