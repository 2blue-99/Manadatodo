package com.blue.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blue.database.dao.TodoDao
import com.blue.database.model.TodoEntity

@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getDao(): TodoDao
}