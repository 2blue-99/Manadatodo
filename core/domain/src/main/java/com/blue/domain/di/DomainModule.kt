package com.blue.domain.di

import com.blue.data.repo.SupaBaseRepo
import com.blue.data.repo.DataStoreRepoImpl
import com.blue.data.repo.DatabaseRepoImpl
import com.blue.domain.auth.GetAuthUseCase
import com.blue.domain.database.DeleteDatabaseUseCase
import com.blue.domain.database.GetDatabaseUseCase
import com.blue.domain.database.InsertDatabaseUseCase
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
    fun provideSupaUseCase(repo: SupaBaseRepo): GetAuthUseCase = GetAuthUseCase(repo)

    @Singleton
    @Provides
    fun provideDataStoreUseCase(repo: DataStoreRepoImpl): GetDataStoreUpdateUseCase = GetDataStoreUpdateUseCase(repo)


    @Singleton
    @Provides
    fun provideDeleteDataBaseUseCase(repo: DatabaseRepoImpl): DeleteDatabaseUseCase = DeleteDatabaseUseCase(repo)

    @Singleton
    @Provides
    fun provideGetDataBaseUseCase(repo: DatabaseRepoImpl): GetDatabaseUseCase = GetDatabaseUseCase(repo)

    @Singleton
    @Provides
    fun provideInsertDataBaseUseCase(repo: DatabaseRepoImpl): InsertDatabaseUseCase = InsertDatabaseUseCase(repo)

//    @Singleton
//    @Provides
//    fun provideGetSelectedDataBaseUseCase(repo: DatabaseRepoImpl): InsertDatabaseUseCase = InsertDatabaseUseCase(repo)
}