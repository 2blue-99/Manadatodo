package com.blue.domain.datastore

import com.blue.data.repo.datastore.DataStoreRepoImpl
import javax.inject.Inject

class UpdateInitStateUseCase @Inject constructor(
    private val repo: DataStoreRepoImpl
) {
    suspend operator fun invoke(state: Boolean){
        repo.updateLastUpdateDateTime(state)
    }
}