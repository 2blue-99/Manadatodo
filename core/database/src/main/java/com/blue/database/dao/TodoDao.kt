package com.blue.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.blue.database.model.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert
    fun insertData(todoEntity: TodoEntity)

    @Query("Select * From TodoList")
    fun readAllData(): Flow<List<TodoEntity>>

    @Query("Delete From TodoList Where id = :id")
    fun deleteData(id: Int): Int

}