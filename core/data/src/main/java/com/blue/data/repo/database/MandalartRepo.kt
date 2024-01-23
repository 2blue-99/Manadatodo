package com.blue.data.repo.database

import com.blue.data.work.Syncable
import com.blue.model.Mandalart
import kotlinx.coroutines.flow.Flow

interface MandalartRepo : Syncable {
    suspend fun insertMandalart(list: List<Mandalart>)

    fun readAllMandalart(): Flow<List<Mandalart>>

    suspend fun deleteAllMandalart()
}