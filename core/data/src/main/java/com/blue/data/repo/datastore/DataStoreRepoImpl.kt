package com.blue.data.repo.datastore

import com.blue.datastore.DataStoreDataSourceImpl
//import com.blue.work.status.RequestType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepoImpl @Inject constructor(
    private val dataStore: DataStoreDataSourceImpl
): DataStoreRepo {
    override suspend fun updateToken(token: String) {
        dataStore.updateToken(token)
    }

    override fun dataStoreFlow(): Flow<String> {
        return dataStore.tokenFlow()
    }

    override suspend fun updateInitState(state: Boolean) {
        dataStore.updateInitState(state)
    }

    override fun getInitState(): Flow<Boolean> {
        return dataStore.getInitState()
    }
}