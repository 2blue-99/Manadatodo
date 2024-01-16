package com.blue.domain.database

import com.blue.data.repo.DatabaseRepo
import javax.inject.Inject

class DeleteDatabaseUseCase @Inject constructor(
    private val database: DatabaseRepo
) {
    operator fun invoke(id: Int): Int =
        database.deleteData(id)
}