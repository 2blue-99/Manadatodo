package com.blue.data.repo.supabase

import com.blue.data.work.Syncable
import com.blue.model.Mandalart
import com.blue.model.Todo
import io.github.jan.supabase.compose.auth.ComposeAuth

interface SupabaseRepo : Syncable {
    fun getAuth(): ComposeAuth
    fun getToken(): String?
    suspend fun insertTodo(data: Todo)
    suspend fun deleteTodo(id: Long)
    suspend fun insertMandalart(data: Mandalart)
    suspend fun deleteMandalart(id: Long)
}