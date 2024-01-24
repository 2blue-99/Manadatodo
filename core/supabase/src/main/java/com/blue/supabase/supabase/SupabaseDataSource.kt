package com.blue.supabase.supabase

import com.blue.model.Mandalart
import com.blue.model.Todo
import dagger.Provides
import io.github.jan.supabase.compose.auth.ComposeAuth

interface SupabaseDataSource {
//    fun getAuth(): ComposeAuth

    fun getToken(): String?

    suspend fun insertTodo(data: Todo)

    suspend fun deleteTodo(id: Int)

    suspend fun insertMandalart(data: Mandalart)

    suspend fun deleteMandalart(id: Int)
}