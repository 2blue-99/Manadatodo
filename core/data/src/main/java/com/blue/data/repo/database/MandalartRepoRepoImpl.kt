package com.blue.data.repo.database

import com.blue.data.work.status.RequestType
import com.blue.database.local.dao.MandalartDao
import com.blue.database.local.model.MandalartEntity
import com.blue.database.local.model.toMandalart
import com.blue.model.Mandalart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class MandalartRepoRepoImpl @Inject constructor(
    private val mandalartDao: MandalartDao
) : MandalartRepo {
    override suspend fun insertMandalart(list: List<Mandalart>) {
        mandalartDao.insertMandalart(list.map { MandalartEntity(it.id, it.cnt) })
    }

    override fun readAllMandalart(): Flow<List<Mandalart>> =
        mandalartDao.readAllMandalart().map { it.map { it.toMandalart() } }


    override suspend fun deleteAllMandalart() {
        mandalartDao.deleteAllMandalart()
    }

    override suspend fun syncWith(typeData: RequestType): Boolean {
        return true
    }

//    override suspend fun syncWith(data: RequestType): Boolean {
//        return true
//    }
}