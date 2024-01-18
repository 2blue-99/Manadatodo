package com.blue.domain.database.mandalart

import com.blue.data.repo.database.MandalartRepo
import com.blue.model.Mandalart
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMandalartUseCase @Inject constructor(
    private val mandalartRepo: MandalartRepo
) {
    operator fun invoke(): Flow<List<Mandalart>> =
        mandalartRepo.readAllMandalart()
}