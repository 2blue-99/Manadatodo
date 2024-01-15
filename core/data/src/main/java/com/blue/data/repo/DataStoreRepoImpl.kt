package com.blue.data.repo

import com.blue.datastore.DataStoreDataSourceImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepoImpl @Inject constructor(
    private val authHelper: DataStoreDataSourceImpl
): DataStoreRepo {
    override suspend fun updateToken(token: String) {
        authHelper.updateAuth(token)
    }

    override fun dataStoreFlow(): Flow<String> {
        return authHelper.authFlow()
    }

}