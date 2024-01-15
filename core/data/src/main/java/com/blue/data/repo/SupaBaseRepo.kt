package com.blue.data.repo

import io.github.jan.supabase.compose.auth.ComposeAuth

interface SupaBaseRepo {
    fun getAuth(): ComposeAuth
    fun getToken(): String?
}