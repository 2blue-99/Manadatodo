package com.blue.data.repo.datastore

import com.blue.datastore.DataStoreDataSourceImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepoImpl @Inject constructor(
    private val authHelper: DataStoreDataSourceImpl
): DataStoreRepo {
    override suspend fun updateToken(token: String) {
        authHelper.updateToken(token)
    }

    override fun dataStoreFlow(): Flow<String> {
        return authHelper.tokenFlow()
    }
}