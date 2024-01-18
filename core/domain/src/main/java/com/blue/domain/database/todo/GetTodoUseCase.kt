package com.blue.domain.database.todo

import com.blue.data.repo.database.TodoRepo
import com.blue.model.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodoUseCase @Inject constructor(
    private val database: TodoRepo
) {
    operator fun invoke(): Flow<List<Todo>> =
        database.readAllData()
}