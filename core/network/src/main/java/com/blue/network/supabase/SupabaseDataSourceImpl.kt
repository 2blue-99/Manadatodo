package com.blue.network.supabase

import android.util.Log
import com.mandatodo.core.network.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SupabaseDataSourceImpl @Inject constructor() : SupabaseDataSource {
    private var client: SupabaseClient = createSupabaseClient(
        supabaseUrl = BuildConfig.Url,
        supabaseKey = BuildConfig.Key
    ) {
        install(Auth)
        install(ComposeAuth) {
            googleNativeLogin(serverClientId = BuildConfig.Id)
        }
        install(Postgrest)
    }

    override fun getAuth(): ComposeAuth = client.composeAuth
    override fun getToken(): String? = client.auth.currentAccessTokenOrNull()
}