package com.blue.domain.datastore

import com.blue.data.repo.DataStoreRepoImpl
import javax.inject.Inject

class GetDataStoreUpdateUseCase @Inject constructor(
    private val repo: DataStoreRepoImpl
) {
    suspend operator fun invoke(token: String){
        repo.updateToken(token)
    }
}