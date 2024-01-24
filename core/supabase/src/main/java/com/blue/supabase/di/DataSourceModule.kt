package com.blue.supabase.di

import com.blue.supabase.supabase.SupabaseDataSource
import com.blue.supabase.supabase.SupabaseDataSourceImpl
import com.manadatodo.core.supabase.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideSupabaseClient(): SupabaseClient = createSupabaseClient(
        supabaseUrl = BuildConfig.Url,
        supabaseKey = BuildConfig.Key
    ) {
        install(Auth)
        install(ComposeAuth) {
            googleNativeLogin(serverClientId = BuildConfig.Id)
        }
        install(Postgrest)
    }

    @Singleton
    @Provides
    fun provideComposeAuth(client: SupabaseClient): ComposeAuth = client.composeAuth

    @Singleton
    @Provides
    fun provideSupaRepository(client: SupabaseClient): SupabaseDataSource =
        SupabaseDataSourceImpl(client)

}