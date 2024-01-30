package com.blue.database.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blue.database.local.model.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(todoEntity: List<TodoEntity>): List<Long>

    @Query("Select * From TodoList Where isDeleted = 0")
    fun readAllDataFlow(): Flow<List<TodoEntity>>

    @Query("Select * From TodoList Where updateDateTime = :date")
    fun readSelectedDataFlow(date: String): Flow<List<TodoEntity>>

    @Query("Select * From TodoList Where updateDateTime > :date")
    fun readToUpdateData(date: String): List<TodoEntity>

    //    @Query("Delete From TodoList Where id = :id")
    @Query("Update TodoList Set isDeleted = 1, updateDateTime = :updateTime Where id= :id")
    suspend fun deleteData(id: List<Long>, updateTime: String): Int

    @Query("Update TodoList Set isDone = :status Where id = :id")
    suspend fun changeCheckBox(id: Long, status: Boolean)
}