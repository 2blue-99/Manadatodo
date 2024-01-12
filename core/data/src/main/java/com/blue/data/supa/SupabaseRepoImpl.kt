package com.blue.data.supa

import com.blue.data.SupaBaseRepo
import com.blue.network.supabase.SupabaseDataSource
import io.github.jan.supabase.compose.auth.ComposeAuth
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SupabaseRepoImpl @Inject constructor(
    private val dataSource: SupabaseDataSource
): SupaBaseRepo {
    override fun getAuth(): ComposeAuth = dataSource.getAuth()
}