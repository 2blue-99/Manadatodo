package com.blue.database.remote.di

import com.blue.database.remote.supabase.SupabaseDataSource
import com.blue.database.remote.supabase.SupabaseDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideSupaRepository(): SupabaseDataSource =
        SupabaseDataSourceImpl()

}