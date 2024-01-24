package com.blue.database.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blue.database.local.dao.MandalartDao
import com.blue.database.local.dao.TodoDao
import com.blue.database.local.model.MandalartEntity
import com.blue.database.local.model.TodoEntity

@Database(
    entities = [
        TodoEntity::class,
        MandalartEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getTodoDao(): TodoDao
    abstract fun getMandalartDao(): MandalartDao
}