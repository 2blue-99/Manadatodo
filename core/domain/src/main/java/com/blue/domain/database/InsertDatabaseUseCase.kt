package com.blue.domain.database

import com.blue.data.repo.DatabaseRepo
import com.blue.database.model.TodoEntity
import com.blue.model.Todo
import javax.inject.Inject

class InsertDatabaseUseCase @Inject constructor(
    private val database: DatabaseRepo
) {
    suspend operator fun invoke(data: Todo) =
        database.insertData(data)
}