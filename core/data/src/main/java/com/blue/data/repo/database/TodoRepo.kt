package com.blue.data.repo.database

import com.blue.data.work.Syncable
import com.blue.database.local.model.TodoEntity
import com.blue.model.Todo
import com.blue.supabase.model.TodoModel
import kotlinx.coroutines.flow.Flow

interface TodoRepo : Syncable {
    suspend fun insertData(list: List<Todo>)
    suspend fun insertSyncData(list:List<TodoModel>)
    fun readAllDataFlow(): Flow<List<TodoEntity>>
    suspend fun deleteData(id: Long)
    suspend fun changeCheckBox(id: Long, status: Boolean)
    fun readSelectedDataFlow(date: String): Flow<List<Todo>>
    fun readToUpdateData(date: String): List<Todo>
}