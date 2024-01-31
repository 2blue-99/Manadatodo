package com.blue.data.repo.supabase

import com.blue.data.work.Syncable
import com.blue.database.local.model.TodoEntity
import com.blue.model.Mandalart
import com.blue.supabase.model.TodoModel
import io.github.jan.supabase.compose.auth.ComposeAuth

interface SupabaseRepo : Syncable {
    fun getAuth(): ComposeAuth
    fun getToken(): String?

    suspend fun insertTodoData(data: List<TodoEntity>): List<Long>
    suspend fun insertMandalartData(data: Mandalart)
    suspend fun readUpdatedTodoData(date: String): List<TodoModel>
    suspend fun updateTodoData(id: List<TodoEntity>): Boolean
    suspend fun deleteMandalartData(id: Long)
}