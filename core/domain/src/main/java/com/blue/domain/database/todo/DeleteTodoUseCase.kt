package com.blue.domain.database.todo

import com.blue.data.repo.database.TodoRepo
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(
    private val database: TodoRepo
) {
    suspend operator fun invoke(id: Long) =
        database.deleteData(id)
}