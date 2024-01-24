package com.blue.database.local.di

import com.blue.database.local.AppDataBase
import com.blue.database.local.dao.MandalartDao
import com.blue.database.local.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideTodoDao(
        database: AppDataBase
    ): TodoDao = database.getTodoDao()

    @Provides
    fun provideMandalartDao(
        database: AppDataBase
    ): MandalartDao = database.getMandalartDao()
}