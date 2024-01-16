package com.blue.daily

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blue.database.model.TodoEntity
import com.blue.domain.database.DeleteDatabaseUseCase
import com.blue.domain.database.GetDatabaseUseCase
import com.blue.domain.database.InsertDatabaseUseCase
import com.blue.domain.datastore.GetDataStoreFlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DailyViewModel @Inject constructor(
    private val getAllDataUseCase: GetDatabaseUseCase,
    private val insertDataUseCase: InsertDatabaseUseCase,
    private val deleteDataUseCase: DeleteDatabaseUseCase
): ViewModel() {

    fun getAllData(): Flow<List<TodoEntity>> = getAllDataUseCase()

    fun insertData(data: TodoEntity){
        viewModelScope.launch {
            insertDataUseCase(data)
        }
    }

    fun deleteData(id: Int){
        viewModelScope.launch {
            deleteDataUseCase(id)
        }
    }
}