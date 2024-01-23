package com.blue.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blue.database.model.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(todoEntity: TodoEntity): Long

    @Query("Select * From TodoList")
    fun readAllData(): Flow<List<TodoEntity>>

    @Query("Delete From TodoList Where id = :id")
    suspend fun deleteData(id: Int)

    @Query("Update TodoList Set isDone = :status Where id = :id")
    suspend fun changeCheckBox(id: Int, status: Boolean)

    @Query("Select * From TodoList Where date = :date")
    fun readSelectedData(date: String): Flow<List<TodoEntity>>
}