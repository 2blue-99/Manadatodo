package com.blue.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreRepo {
    suspend fun updateToken()
    fun authFlow(): Flow<String>
}