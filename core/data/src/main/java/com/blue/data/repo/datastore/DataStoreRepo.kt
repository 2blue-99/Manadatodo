package com.blue.data.repo.datastore

import com.blue.data.Syncable
import kotlinx.coroutines.flow.Flow

interface DataStoreRepo : Syncable {

    suspend fun updateToken(token: String)

    fun dataStoreFlow(): Flow<String>

}