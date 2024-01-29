package com.blue.data.repo.database

import com.blue.data.work.Syncable
import com.blue.database.local.model.TodoEntity
import com.blue.model.Todo
import com.blue.supabase.model.TodoModel
import kotlinx.coroutines.flow.Flow

interface TodoRepo : Syncable {
    fun readAllDataFlow(): Flow<List<Todo>>
    fun readSelectedData(date:String): Flow<List<Todo>>
    suspend fun readToUpdateData(date: String): List<TodoEntity>
    suspend fun insertData(list: List<Todo>)
    suspend fun insertTodoModelSyncData(list:List<TodoModel>)
    suspend fun insertTodoEntitySyncData(list:List<TodoEntity>)
    suspend fun deleteData(id: List<Long>)
    suspend fun deleteSyncData(id: List<Long>)
    suspend fun changeCheckBox(id: Long, status: Boolean)
//    fun readToUpdateData(date: String): List<Todo>
}