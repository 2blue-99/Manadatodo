package com.blue.domain.database.mandalart

import com.blue.data.repo.database.MandalartRepo
import javax.inject.Inject

class InsertMandalartUseCase @Inject constructor(
    private val mandalartRepo: MandalartRepo
) {
    suspend operator fun invoke(id: Int, cnt: Int){
        mandalartRepo.insertMandalart(id, cnt)
    }
}