package com.blue.domain.database

import com.blue.data.repo.DatabaseRepo
import javax.inject.Inject

class DeleteDatabaseUseCase @Inject constructor(
    private val database: DatabaseRepo
) {
    suspend operator fun invoke(id: Int) =
        database.deleteData(id)
}