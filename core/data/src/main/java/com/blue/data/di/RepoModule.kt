package com.blue.data.di

import com.blue.data.SupaBaseRepo
import com.blue.data.supa.SupabaseRepoImpl
import com.blue.network.supabase.SupabaseDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Singleton
    @Provides
    fun provideSupaRepository(dataSource: SupabaseDataSourceImpl): SupaBaseRepo =
        SupabaseRepoImpl(dataSource)
}