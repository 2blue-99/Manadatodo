package com.blue.data.di

import com.blue.data.repo.database.MandalartRepo
import com.blue.data.repo.database.MandalartRepoRepoImpl
import com.blue.data.repo.datastore.DataStoreRepo
import com.blue.data.repo.datastore.DataStoreRepoImpl
import com.blue.data.repo.database.TodoRepo
import com.blue.data.repo.database.TodoRepoImpl
import com.blue.data.repo.supabase.SupabaseRepo
import com.blue.data.repo.supabase.SupabaseRepoImpl
import com.blue.data.work.status.SyncRequestRepo
import com.blue.database.local.AppDataBase
import com.blue.datastore.DataStoreDataSourceImpl
import com.blue.supabase.supabase.SupabaseDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.compose.auth.ComposeAuth
import javax.inject.Singleton

// Todo
@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    // Supabase Auth
    @Singleton
    @Provides
    fun provideSupaRepo(dataSource: SupabaseDataSourceImpl, auth: ComposeAuth): SupabaseRepo =
        SupabaseRepoImpl(
            supaDataSource = dataSource,
            composeAuth = auth
        )

    // DataStore
    @Singleton
    @Provides
    fun provideDataStoreRepo(authHelper: DataStoreDataSourceImpl): DataStoreRepo =
        DataStoreRepoImpl(authHelper)


    // Todo
    @Singleton
    @Provides
    fun provideTodoRepo(dataBase: AppDataBase, supabaseRepo: SupabaseRepo, syncRequestRepo: SyncRequestRepo, dataStoreRepo: DataStoreRepo): TodoRepo =
        TodoRepoImpl(
            todoDao = dataBase.getTodoDao(),
            supabaseRepo = supabaseRepo,
            syncRequest = syncRequestRepo,
            dataStoreRepo = dataStoreRepo
        )


    // Mandalart
    @Singleton
    @Provides
    fun provideMandalartRepo(dataBase: AppDataBase): MandalartRepo =
        MandalartRepoRepoImpl(dataBase.getMandalartDao())
}