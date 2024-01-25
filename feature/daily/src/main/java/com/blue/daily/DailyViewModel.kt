package com.blue.daily

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.daily.state.BottomSheetUiState
import com.blue.daily.state.DailyUiState
import com.blue.daily.state.TodoUiState
import com.blue.domain.database.todo.ChangeCheckBoxUseCase
import com.blue.domain.database.todo.DeleteTodoUseCase
import com.blue.domain.database.todo.GetTodoUseCase
import com.blue.domain.database.todo.InsertTodoUseCase
import com.blue.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val getAllDataUseCase: GetTodoUseCase,
    private val insertDataUseCase: InsertTodoUseCase,
    private val deleteDataUseCase: DeleteTodoUseCase,
    private val changeCheckDataUseCase: ChangeCheckBoxUseCase,
) : ViewModel() {

    private val _bottomSheetState = MutableStateFlow<BottomSheetUiState>(BottomSheetUiState.Down)
    val bottomSheetUiState: StateFlow<BottomSheetUiState> get() = _bottomSheetState

    val dailyUiState: StateFlow<DailyUiState> =
        getAllDataUseCase().map {
            Log.e("TAG", "getAllDataUseCase $it: ")
            DailyUiState.Success(
                today = LocalDate.now().toString(),
                totalCnt = it.size,
                doneCnt = it.count { it.isDone },
                todoList = it
            )
        }.catch {
            DailyUiState.Error(it.message ?: "err")
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DailyUiState.Loading
        )

    fun changeBottomSheet(isShow: Boolean, uiState: TodoUiState = TodoUiState.Default()){
        if(isShow) {
            _bottomSheetState.value = BottomSheetUiState.Up(uiState)
        }else{
            _bottomSheetState.value = BottomSheetUiState.Down
        }
    }

    fun insertData(data: Todo) {
        Log.e("TAG", "insertData: $data", )
        viewModelScope.launch {
            insertDataUseCase(data)
        }
    }

    fun deleteData(id: Long) {
        viewModelScope.launch {
            deleteDataUseCase(id)
        }
    }

    fun changeCheckBox(id: Long, status: Boolean) {
        viewModelScope.launch {
            changeCheckDataUseCase(id, status)
        }
    }
}