package com.blue.supabase.supabase

import dagger.Provides
import io.github.jan.supabase.compose.auth.ComposeAuth

interface SupabaseDataSource {
    fun getAuth(): ComposeAuth

    fun getToken(): String?
}