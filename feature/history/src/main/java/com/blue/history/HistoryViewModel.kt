package com.blue.history

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.domain.database.GetSelectedDataBaseUseCase
import com.blue.history.state.HistoryUiState
import com.blue.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getSelectedDataBaseUseCase: GetSelectedDataBaseUseCase
) : ViewModel() {

    private val _historyUiState = MutableStateFlow<HistoryUiState>(HistoryUiState.Loading)
    val historyUiState: StateFlow<HistoryUiState> get() = _historyUiState

    fun getSelectedData(date: String) {
        Log.e("TAG", "getSelectedData:", )
        viewModelScope.launch {
            getSelectedDataBaseUseCase(date).collect{
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