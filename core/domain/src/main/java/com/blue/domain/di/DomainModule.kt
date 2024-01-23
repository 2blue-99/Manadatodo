package com.blue.domain.di

import com.blue.data.repo.supabase.SupabaseRepo
import com.blue.data.repo.datastore.DataStoreRepoImpl
import com.blue.data.repo.database.TodoRepoImpl
import com.blue.domain.auth.GetAuthUseCase
import com.blue.domain.database.todo.DeleteTodoUseCase
import com.blue.domain.database.todo.GetTodoUseCase
import com.blue.domain.database.todo.InsertTodoUseCase
import com.blue.domain.datastore.GetDataStoreUpdateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideSupaUseCase(repo: SupabaseRepo): GetAuthUseCase = GetAuthUseCase(repo)

    @Singleton
    @Provides
    fun provideDataStoreUseCase(repo: DataStoreRepoImpl): GetDataStoreUpdateUseCase = GetDataStoreUpdateUseCase(repo)


    @Singleton
    @Provides
    fun provideDeleteDataBaseUseCase(repo: TodoRepoImpl): DeleteTodoUseCase = DeleteTodoUseCase(repo)

    @Singleton
    @Provides
    fun provideGetDataBaseUseCase(repo: TodoRepoImpl): GetTodoUseCase = GetTodoUseCase(repo)

    @Singleton
    @Provides
    fun provideInsertDataBaseUseCase(repo: TodoRepoImpl): InsertTodoUseCase = InsertTodoUseCase(repo)


//    @Singleton
//    @Provides
//    fun provideGetSelectedDataBaseUseCase(repo: DatabaseRepoImpl): InsertDatabaseUseCase = InsertDatabaseUseCase(repo)
}