package com.blue.data.repo.database

import com.blue.model.Mandalart
import kotlinx.coroutines.flow.Flow

interface MandalartRepo {
    suspend fun insertMandalart(id: Int, cnt: Int)

    fun readAllMandalart(): Flow<List<Mandalart>>

    suspend fun deleteAllMandalart()
}