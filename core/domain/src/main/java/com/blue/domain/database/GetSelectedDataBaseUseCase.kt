package com.blue.domain.database

import com.blue.data.repo.DatabaseRepo
import com.blue.model.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedDataBaseUseCase @Inject constructor(
    private val repo: DatabaseRepo
) {
    operator fun invoke(date: String): Flow<List<Todo>> =
        repo.readSelectedData(date)
}