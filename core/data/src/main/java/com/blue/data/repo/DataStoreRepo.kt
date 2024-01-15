package com.blue.data.repo

import kotlinx.coroutines.flow.Flow

interface DataStoreRepo {

    suspend fun updateToken(token: String)

    fun dataStoreFlow(): Flow<String>

}