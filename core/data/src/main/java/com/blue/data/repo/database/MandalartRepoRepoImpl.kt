package com.blue.data.repo.database

import com.blue.database.dao.MandalartDao
import com.blue.database.model.toMandalart
import com.blue.model.Mandalart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class MandalartRepoRepoImpl @Inject constructor(
    private val database: MandalartDao
) : MandalartRepo {
    override suspend fun insertMandalart(id: Int, cnt: Int) {
        database.insertMandalart(id, cnt)
    }

    override fun readAllMandalart(): Flow<List<Mandalart>> =
        database.readAllMandalart().map { it.map { it.toMandalart() } }


    override suspend fun deleteAllMandalart() {
        database.deleteAllMandalart()
    }
}