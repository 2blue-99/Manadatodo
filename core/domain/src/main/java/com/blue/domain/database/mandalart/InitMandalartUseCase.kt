package com.blue.domain.database.mandalart

import com.blue.data.repo.database.MandalartRepo
import javax.inject.Inject

class InitMandalartUseCase @Inject constructor(
    private val mandalartRepo: MandalartRepo
) {
    suspend operator fun invoke(){
        repeat(9){ mandalartRepo.insertMandalart(it, 0) }
    }
}