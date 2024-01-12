package com.blue.data

import io.github.jan.supabase.compose.auth.ComposeAuth

interface SupaBaseRepo {
    fun getAuth(): ComposeAuth
}