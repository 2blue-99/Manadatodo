package com.blue.data.repo.supabase

import com.blue.data.work.status.RequestType
import com.blue.network.supabase.SupabaseDataSource
//import com.blue.work.status.RequestType
import io.github.jan.supabase.compose.auth.ComposeAuth
import javax.inject.Inject


class SupabaseRepoImpl @Inject constructor(
    private val dataSource: SupabaseDataSource
): SupabaseRepo {

    override fun getAuth(): ComposeAuth = dataSource.getAuth()
    override fun getToken(): String? = dataSource.getToken()
    override suspend fun syncWith(typeData: RequestType): Boolean {
        return true
    }
//    override suspend fun syncWith(data: RequestType): Boolean {
//        return true
//    }
}