package com.blue.data.repo

import com.blue.database.model.TodoEntity
import com.blue.model.Todo
import kotlinx.coroutines.flow.Flow

interface DatabaseRepo {
    suspend fun insertData(data: TodoEntity)

    fun readAllData(): Flow<List<Todo>>
    suspend fun deleteData(id: Int)
    suspend fun changeCheckData(id: Int, status: Boolean)
}