package com.blue.mandalart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.domain.database.mandalart.DeleteAllMandalartUseCase
import com.blue.domain.database.mandalart.GetAllMandalartUseCase
import com.blue.domain.database.mandalart.InsertMandalartUseCase
import com.blue.mandalart.uistate.MandalartUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MandalartViewModel @Inject constructor(
    private val readAllMandalartUseCase: GetAllMandalartUseCase,
    private val insertMandalartUseCase: InsertMandalartUseCase,
    private val deleteAllMandalartUseCase: DeleteAllMandalartUseCase
) : ViewModel() {

    val getAllMandalart: Flow<MandalartUiState> =
        readAllMandalartUseCase().map {
            MandalartUiState.Success(
                sum = it.size,
                mandalart = it
            )
        }.catch {
            MandalartUiState.Error(msg = it.message ?: "error")
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MandalartUiState.Loading
        )

    fun deleteAllMandalart(){
        viewModelScope.launch {
            deleteAllMandalartUseCase()
        }
    }

    fun insertMandalart(id: Int, cnt: Int){
        viewModelScope.launch {
            insertMandalartUseCase(id, cnt)
        }
    }
}