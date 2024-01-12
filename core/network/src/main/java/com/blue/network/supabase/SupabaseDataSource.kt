package com.blue.network.supabase

import io.github.jan.supabase.compose.auth.ComposeAuth

interface SupabaseDataSource {
    fun getAuth(): ComposeAuth
}