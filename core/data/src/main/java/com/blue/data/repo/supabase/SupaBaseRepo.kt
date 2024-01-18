package com.blue.data.repo.supabase

import io.github.jan.supabase.compose.auth.ComposeAuth

interface SupaBaseRepo {
    fun getAuth(): ComposeAuth
    fun getToken(): String?
}