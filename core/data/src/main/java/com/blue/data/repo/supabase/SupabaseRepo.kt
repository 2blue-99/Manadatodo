package com.blue.data.repo.supabase

import com.blue.data.Syncable
import io.github.jan.supabase.compose.auth.ComposeAuth

interface SupabaseRepo : Syncable {
    fun getAuth(): ComposeAuth
    fun getToken(): String?
}