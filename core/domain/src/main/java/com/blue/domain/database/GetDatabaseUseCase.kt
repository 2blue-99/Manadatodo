package com.blue.domain.database

import com.blue.data.repo.DatabaseRepo
import com.blue.database.model.TodoEntity
import com.blue.model.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDatabaseUseCase @Inject constructor(
    private val database: DatabaseRepo
) {
    operator fun invoke(): Flow<List<Todo>> =
        database.readAllData()
}