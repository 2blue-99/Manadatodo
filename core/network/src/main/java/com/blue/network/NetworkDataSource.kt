package com.blue.network

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.ComposeAuth

interface NetworkDataSource {
    fun getAuth(): ComposeAuth
}