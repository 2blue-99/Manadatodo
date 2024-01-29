package com.blue.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreDataSource {
    fun tokenFlow(): Flow<String>

    suspend fun updateToken(token: String)

    suspend fun getLastUpdateDateTime(): String

    suspend fun updateLastUpdateTime(date: String)
}