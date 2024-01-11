package com.blue.network.supabase

import androidx.compose.runtime.Composable
import com.blue.network.NetworkDataSource
import com.mandatodo.core.network.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton


@Singleton
class SupaNetwork: NetworkDataSource {
    private val client = createSupabaseClient(
        supabaseUrl = BuildConfig.Url,
        supabaseKey = BuildConfig.Key
    ) {
        install(Auth)
        install(ComposeAuth) {
            googleNativeLogin(serverClientId = BuildConfig.Id)
        }
        install(Postgrest)
    }

    fun getComposeAuth() = client.composeAuth
    override fun getAuth() = client.composeAuth

}