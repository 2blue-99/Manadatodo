package com.blue.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.domain.database.todo.GetSelectedTodoUseCase
import com.blue.history.state.HistoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getSelectedTodoUseCase: GetSelectedTodoUseCase
) : ViewModel() {

    private val _historyUiState = MutableStateFlow<HistoryUiState>(HistoryUiState.Loading)
    val historyUiState: StateFlow<HistoryUiState> get() = _historyUiState

    fun getSelectedData(date: String) {
        Log.e("TAG", "getSelectedData:", )
        viewModelScope.launch {
            getSelectedTodoUseCase(date).collect{
                _historyUiState.value =
                    HistoryUiState.Success(
                        totalCnt = it.size,
                        doneCnt = it.count { it.isDone },
                        todoList = it
                    )
            }
        }
//        val gap = getSelectedDataBaseUseCase(date).map {
//            Log.e("TAG", "getSelectedData: $it", )
//            _historyUiState.value =
//                HistoryUiState.Success(
//                    totalCnt = it.size,
//                    doneCnt = it.count { it.isDone },
//                    todoList = it
//                )
//        }.catch {
//            HistoryUiState.Error(msg = it.message ?: "err")
//        }.stateIn(
//            scope = CoroutineScope(Dispatchers.IO),
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = HistoryUiState.Loading
//        )
    }
}