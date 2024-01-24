package com.blue.data.repo.supabase

import com.blue.data.work.status.RequestType
import com.blue.supabase.supabase.SupabaseDataSource
//import com.blue.work.status.RequestType
import io.github.jan.supabase.compose.auth.ComposeAuth
import javax.inject.Inject


class SupabaseRepoImpl @Inject constructor(
    private val dataSource: SupabaseDataSource,
    private val composeAuth: ComposeAuth,
): SupabaseRepo {

    override fun getAuth(): ComposeAuth = composeAuth
    override fun getToken(): String? = dataSource.getToken()
    override suspend fun syncWith(typeData: RequestType): Boolean {
        return true
    }
//    override suspend fun syncWith(data: RequestType): Boolean {
//        return true
//    }
}