package com.blue.domain.database.todo

import com.blue.data.repo.database.TodoRepo
import javax.inject.Inject

class ChangeCheckBoxUseCase @Inject constructor(
    private val database: TodoRepo
) {
    suspend operator fun invoke(id: Long, status: Boolean) =
        database.changeCheckBox(id, status)
}