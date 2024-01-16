package com.blue.data.di

import com.blue.data.repo.DataStoreRepo
import com.blue.data.repo.DataStoreRepoImpl
import com.blue.data.repo.DatabaseRepo
import com.blue.data.repo.DatabaseRepoImpl
import com.blue.data.repo.SupaBaseRepo
import com.blue.data.repo.SupabaseRepoImpl
import com.blue.database.AppDataBase
import com.blue.database.dao.TodoDao
import com.blue.datastore.DataStoreDataSourceImpl
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

    @Singleton
    @Provides
    fun provideDataStoreHelper(authHelper: DataStoreDataSourceImpl): DataStoreRepo =
        DataStoreRepoImpl(authHelper)

    @Singleton
    @Provides
    fun provideDatabase(dataBase: AppDataBase): DatabaseRepo =
        DatabaseRepoImpl(dataBase.getDao())
}