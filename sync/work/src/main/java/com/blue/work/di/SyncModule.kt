package com.blue.work.di

import android.content.Context
import com.blue.work.status.SyncRequestInterface
import com.blue.work.status.SyncRequestManager
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
    fun provideSyncRequestManager(@ApplicationContext context: Context): SyncRequestInterface =
        SyncRequestManager(context)

}