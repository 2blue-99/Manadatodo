package com.blue.daily

import androidx.lifecycle.ViewModel
import com.blue.daily.uiState.BottomSheetUiState
import com.blue.daily.uiState.DailyUiState
import com.blue.daily.uiState.TodoUiState
import com.blue.domain.database.ChangeCheckBoxUseCase
import com.blue.domain.database.DeleteDatabaseUseCase
import com.blue.domain.database.GetDatabaseUseCase
import com.blue.domain.database.InsertDatabaseUseCase
import com.blue.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val getAllDataUseCase: GetDatabaseUseCase,
    private val insertDataUseCase: InsertDatabaseUseCase,
    private val deleteDataUseCase: DeleteDatabaseUseCase,
    private val changeCheckDataUseCase: ChangeCheckBoxUseCase,
) : ViewModel() {

    private val _bottomSheetState = MutableStateFlow<BottomSheetUiState>(BottomSheetUiState.Down)
    val bottomSheetUiState: StateFlow<BottomSheetUiState> get() = _bottomSheetState

    val dailyUiState: StateFlow<DailyUiState> =
        getAllDataUseCase().map {
            DailyUiState.Success(
                today = LocalDateTime.now().toString(),
                totalCnt = it.size,
                doneCnt = it.count { it.isDone },
                todoList = it
            )
        }.catch {
            DailyUiState.Error(it.message ?: "err")
        }.stateIn(
            scope = CoroutineScope(Dispatchers.IO),
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
        CoroutineScope(Dispatchers.IO).launch {
            insertDataUseCase(data)
        }
    }

    fun deleteData(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            deleteDataUseCase(id)
        }
    }

    fun changeCheckBox(id: Int, status: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            changeCheckDataUseCase(id, status)
        }
    }
}