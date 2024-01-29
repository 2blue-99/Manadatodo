package com.blue.supabase.supabase

import com.blue.model.Mandalart
import com.blue.model.Todo
import com.blue.supabase.model.MandalartModel
import com.blue.supabase.model.TodoModel
import dagger.Provides
import io.github.jan.supabase.compose.auth.ComposeAuth

interface SupabaseDataSource {
    fun getToken(): String?
    suspend fun readTodo(date: String): List<TodoModel>
    suspend fun insertTodo(data: List<TodoModel>): List<Long>
    suspend fun deleteTodo(id: List<Long>)
    suspend fun insertMandalart(data: MandalartModel)
    suspend fun deleteMandalart(id: Long)
}