package com.blue.database.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blue.database.local.model.MandalartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MandalartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMandalart(mandalartEntity: List<MandalartEntity>)

    @Query("Select * From Mandalart")
    fun readAllMandalart(): Flow<List<MandalartEntity>>

    @Query("Delete From Mandalart")
    suspend fun deleteAllMandalart()

}