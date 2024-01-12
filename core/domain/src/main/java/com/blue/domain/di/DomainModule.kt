package com.blue.domain.di

import com.blue.data.SupaBaseRepo
import com.blue.data.supa.SupabaseRepoImpl
import com.blue.domain.GetAuthUseCase
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
    fun provideSupaUseCase(repo : SupaBaseRepo): GetAuthUseCase
        = GetAuthUseCase(repo)
}