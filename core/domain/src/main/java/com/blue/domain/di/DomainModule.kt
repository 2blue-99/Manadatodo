package com.blue.domain.di

import com.blue.data.repo.SupaBaseRepo
import com.blue.data.repo.DataStoreRepoImpl
import com.blue.domain.auth.GetAuthUseCase
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
}