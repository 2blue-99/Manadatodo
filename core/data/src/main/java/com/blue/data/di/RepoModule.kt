package com.blue.data.di

import com.blue.data.repo.database.MandalartRepo
import com.blue.data.repo.database.MandalartRepoRepoImpl
import com.blue.data.repo.datastore.DataStoreRepo
import com.blue.data.repo.datastore.DataStoreRepoImpl
import com.blue.data.repo.database.TodoRepo
import com.blue.data.repo.database.TodoRepoImpl
import com.blue.data.repo.supabase.SupabaseRepo
import com.blue.data.repo.supabase.SupabaseRepoImpl
import com.blue.data.work.status.SyncRequestInterface
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
    fun provideSupaRepository(dataSource: SupabaseDataSourceImpl, auth: ComposeAuth): SupabaseRepo =
        SupabaseRepoImpl(dataSource, auth)

    // DataStore
    @Singleton
    @Provides
    fun provideDataStoreHelper(authHelper: DataStoreDataSourceImpl): DataStoreRepo =
        DataStoreRepoImpl(authHelper)




    // Todo
    @Singleton
    @Provides
    fun provideTodoDatabase(dataBase: AppDataBase, syncRequestInterface: SyncRequestInterface, supabaseRepo: SupabaseRepo): TodoRepo =
        TodoRepoImpl(dataBase.getTodoDao(), syncRequestInterface, supabaseRepo)





    // Mandalart
    @Singleton
    @Provides
    fun provideMandalartDatabase(dataBase: AppDataBase): MandalartRepo =
        MandalartRepoRepoImpl(dataBase.getMandalartDao())
}