package com.blue.database.di

import com.blue.database.AppDataBase
import com.blue.database.dao.MandalartDao
import com.blue.database.dao.TodoDao
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