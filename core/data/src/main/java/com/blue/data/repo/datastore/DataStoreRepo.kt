package com.blue.data.repo.datastore

import com.blue.data.work.Syncable
import kotlinx.coroutines.flow.Flow

interface DataStoreRepo {

    suspend fun updateToken(token: String)

    fun dataStoreFlow(): Flow<String>

    suspend fun updateInitState(state: Boolean)

    fun getInitState(): Flow<Boolean>
}