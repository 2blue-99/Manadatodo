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
    fun insertData(todoEntity: TodoEntity)

    @Query("Select * From TodoList")
    fun readAllData(): Flow<List<TodoEntity>>

    @Query("Delete From TodoList Where id = :id")
    fun deleteData(id: Int)

    @Query("Update TodoList Set isChecked = :status Where id = :id")
    fun changeCheckData(id: Int, status: Boolean)
}