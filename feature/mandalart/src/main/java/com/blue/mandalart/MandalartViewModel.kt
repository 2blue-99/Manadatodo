package com.blue.mandalart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.domain.database.mandalart.DeleteAllMandalartUseCase
import com.blue.domain.database.mandalart.GetAllMandalartUseCase
import com.blue.domain.database.mandalart.InitMandalartUseCase
import com.blue.domain.database.mandalart.InsertMandalartUseCase
import com.blue.mandalart.uistate.MandalartUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MandalartViewModel @Inject constructor(
    private val readAllMandalartUseCase: GetAllMandalartUseCase,
    private val insertMandalartUseCase: InsertMandalartUseCase,
    private val deleteAllMandalartUseCase: DeleteAllMandalartUseCase,
    private val initMandalartUseCase: InitMandalartUseCase
) : ViewModel() {

    val getAllMandalart: StateFlow<MandalartUiState> =
        readAllMandalartUseCase().map {
            Log.e("TAG", "$it: ", )
            when (it.size) {
                0 -> MandalartUiState.Loading
                else -> MandalartUiState.Success(sum = it.sumOf{it.cnt}, mandalart = it)
            }

        }.catch {
            MandalartUiState.Error(msg = it.message ?: "error")
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MandalartUiState.Loading
        )

    fun deleteAllMandalart() {
        viewModelScope.launch {
            deleteAllMandalartUseCase()
        }
    }

    fun plusMandalart(id: Long, cnt: Int) {
        viewModelScope.launch {
            Log.e("TAG", "plusMandalart: $id, $cnt", )
            insertMandalartUseCase(id, cnt)
        }
    }

    fun initMandalart() {
        viewModelScope.launch {
            initMandalartUseCase()
        }
    }
}