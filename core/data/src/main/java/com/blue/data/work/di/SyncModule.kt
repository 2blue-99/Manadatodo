package com.blue.data.work.di

import android.content.Context
import com.blue.data.work.status.SyncRequestRepo
import com.blue.data.work.status.SyncRequestRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SyncModule {

    @Singleton
    @Provides
    fun provideSyncRequestManager(@ApplicationContext context: Context): SyncRequestRepo =
        SyncRequestRepoImpl(context)

}