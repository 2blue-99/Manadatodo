package com.blue.domain.database.todo

import com.blue.data.repo.database.TodoRepo
import com.blue.model.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedTodoUseCase @Inject constructor(
    private val repo: TodoRepo
) {
    operator fun invoke(date: String): Flow<List<Todo>> =
        repo.readSelectedDataFlow(date)
}