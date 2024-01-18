package com.blue.domain.datastore

import com.blue.data.repo.datastore.DataStoreRepoImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDataStoreFlowUseCase @Inject constructor(
    private val repo: DataStoreRepoImpl
) {
    operator fun invoke(): Flow<String>{
        return repo.dataStoreFlow()
    }
}