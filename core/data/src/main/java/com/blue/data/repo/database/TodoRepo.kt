package com.blue.data.repo.database

import com.blue.data.work.Syncable
import com.blue.database.local.model.TodoEntity
import com.blue.model.Todo
import com.blue.supabase.model.TodoModel
import kotlinx.coroutines.flow.Flow

interface TodoRepo : Syncable {
    suspend fun insertData(data: Todo)

    fun readAllDataFlow(): Flow<List<Todo>>
    fun readSelectedData(date: String): Flow<List<Todo>>


    suspend fun checkAndUpdateSupaData(): Boolean
    suspend fun checkAndUpdateLocalData(): Boolean




    suspend fun deleteData(id: List<Long>)
    suspend fun changeCheckBox(id: Long, status: Boolean)
}