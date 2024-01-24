package com.blue.supabase.di

import com.blue.supabase.supabase.SupabaseDataSource
import com.blue.supabase.supabase.SupabaseDataSourceImpl
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