package com.blue.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.blue.database.model.MandalartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MandalartDao {

    @Insert
    suspend fun insertMandalart(id: Int, cnt: Int)

    @Query("Select * From Mandalart")
    fun readAllMandalart(): Flow<List<MandalartEntity>>

    @Query("Delete From Mandalart")
    suspend fun deleteAllMandalart()

}