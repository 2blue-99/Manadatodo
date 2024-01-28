package com.blue.data.repo.supabase

import com.blue.data.work.Syncable
import com.blue.database.local.model.TodoEntity
import com.blue.model.Mandalart
import com.blue.model.Todo
import com.blue.supabase.model.TodoModel
import io.github.jan.supabase.compose.auth.ComposeAuth

interface SupabaseRepo : Syncable {
    fun getAuth(): ComposeAuth
    fun getToken(): String?
    suspend fun readTodo(date: String): List<TodoModel>
    suspend fun insertTodo(data: Todo)
    suspend fun deleteTodo(id: Long)
    suspend fun insertMandalart(data: Mandalart)
    suspend fun deleteMandalart(id: Long)
}