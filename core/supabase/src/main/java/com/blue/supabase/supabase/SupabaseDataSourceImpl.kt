package com.blue.supabase.supabase

import com.blue.model.Mandalart
import com.blue.model.Todo
import com.manadatodo.core.supabase.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SupabaseDataSourceImpl @Inject constructor(
    private val client: SupabaseClient
) : SupabaseDataSource {
//    private var client: SupabaseClient = createSupabaseClient(
//        supabaseUrl = BuildConfig.Url,
//        supabaseKey = BuildConfig.Key
//    ) {
//        install(Auth)
//        install(ComposeAuth) {
//            googleNativeLogin(serverClientId = BuildConfig.Id)
//        }
//        install(Postgrest)
//    }
//    override fun getAuth(): ComposeAuth = client.composeAuth
    override fun getToken(): String? = client.auth.currentAccessTokenOrNull()
    override suspend fun insertTodo(data: Todo) {
//        val result = client.postgrest["Todo"].insert()
    }

    override suspend fun deleteTodo(id: Int) {

    }

    override suspend fun insertMandalart(data: Mandalart) {

    }

    override suspend fun deleteMandalart(id: Int) {

    }
}