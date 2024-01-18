package com.blue.data.repo.supabase

import com.blue.network.supabase.SupabaseDataSource
import io.github.jan.supabase.compose.auth.ComposeAuth
import javax.inject.Inject


class SupabaseRepoImpl @Inject constructor(
    private val dataSource: SupabaseDataSource
): SupaBaseRepo {

    override fun getAuth(): ComposeAuth = dataSource.getAuth()
    override fun getToken(): String? = dataSource.getToken()
}