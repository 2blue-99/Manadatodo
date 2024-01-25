package com.blue.supabase.supabase

import com.blue.model.Mandalart
import com.blue.model.Todo
import com.blue.supabase.model.MandalartModel
import com.blue.supabase.model.TodoModel
import dagger.Provides
import io.github.jan.supabase.compose.auth.ComposeAuth

interface SupabaseDataSource {
    fun getToken(): String?
    suspend fun insertTodo(data: TodoModel)
    suspend fun deleteTodo(id: Long)
    suspend fun insertMandalart(data: MandalartModel)
    suspend fun deleteMandalart(id: Long)
}