package com.blue.domain.database.todo

import com.blue.data.repo.database.TodoRepo
import com.blue.model.Todo
import javax.inject.Inject

class InsertTodoUseCase @Inject constructor(
    private val database: TodoRepo
) {
    suspend operator fun invoke(data: List<Todo>) =
        database.insertData(data)
}