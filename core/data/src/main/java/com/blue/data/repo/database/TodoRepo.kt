package com.blue.data.repo.database

import com.blue.data.work.Syncable
import com.blue.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepo : Syncable {
    suspend fun insertData(data: Todo)
    fun readAllDataFlow(): Flow<List<Todo>>
    suspend fun deleteData(id: Long)
    suspend fun changeCheckBox(id: Long, status: Boolean)
    fun readSelectedDataFlow(date: String): Flow<List<Todo>>
    fun readToUpdateData(date: String): List<Todo>
}