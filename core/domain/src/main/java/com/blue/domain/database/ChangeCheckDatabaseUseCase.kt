package com.blue.domain.database

import com.blue.data.repo.DatabaseRepo
import com.blue.database.model.TodoEntity
import javax.inject.Inject

class ChangeCheckDatabaseUseCase @Inject constructor(
    private val database: DatabaseRepo
) {
    suspend operator fun invoke(id: Int, status: Boolean) =
        database.changeCheckData(id, status)
}