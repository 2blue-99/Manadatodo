package com.blue.datastore.di

import com.blue.datastore.DataStoreDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

//    @Singleton
//    @Provides
//    fun provideDataStoreHelper(authHelper: DataStoreDataSourceImpl): com.blue.data.repo.DataStoreRepo =
//        com.blue.data.repo.DataStoreRepoImpl(authHelper)
}