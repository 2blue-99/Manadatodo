package com.blue.daily

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.database.model.TodoEntity
import com.blue.domain.database.ChangeCheckDatabaseUseCase
import com.blue.domain.database.DeleteDatabaseUseCase
import com.blue.domain.database.GetDatabaseUseCase
import com.blue.domain.database.InsertDatabaseUseCase
import com.blue.domain.datastore.GetDataStoreFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val getAllDataUseCase: GetDatabaseUseCase,
    private val insertDataUseCase: InsertDatabaseUseCase,
    private val deleteDataUseCase: DeleteDatabaseUseCase,
    private val changeCheckDataUseCase: ChangeCheckDatabaseUseCase
): ViewModel() {

    fun getAllData(): Flow<List<TodoEntity>> = getAllDataUseCase()

    fun insertData(data: TodoEntity){
        CoroutineScope(Dispatchers.IO).launch {
            insertDataUseCase(data)
        }
    }

    fun deleteData(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            deleteDataUseCase(id)
        }
    }

    fun changeCheckData(id: Int, status: Boolean){
        CoroutineScope(Dispatchers.IO).launch {
            changeCheckDataUseCase(id, status)
        }
    }
}