package com.blue.data.repo

import com.blue.database.AppDataBase
import com.blue.database.dao.TodoDao
import com.blue.database.model.TodoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DatabaseRepoImpl @Inject constructor(
    private val dataBase: TodoDao
): DatabaseRepo {
    override suspend fun insertData(data: TodoEntity) {
        dataBase.insertData(data)
    }

    override fun readAllData(): Flow<List<TodoEntity>> =
        dataBase.readAllData()

    override fun deleteData(id: Int): Int =
        dataBase.deleteData(id)
}