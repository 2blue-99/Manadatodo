package com.blue.supabase.supabase

import com.blue.supabase.model.MandalartModel
import com.blue.supabase.model.TodoModel

interface SupabaseDataSource {
    fun getToken(): String?
    suspend fun readUpdatedData(date: String): List<TodoModel>
    suspend fun insertTodoData(data: List<TodoModel>): List<Long>
    suspend fun deleteTodoData(id: List<Long>)
    suspend fun insertMandalartData(data: MandalartModel)
    suspend fun deleteMandalartData(id: Long)
}