package com.blue.network.di

import com.blue.network.supabase.SupabaseDataSourceImpl
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
    fun provideSupaRepository(): SupabaseDataSourceImpl =
        SupabaseDataSourceImpl()

}