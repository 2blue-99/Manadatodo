package com.blue.domain.database.mandalart

import com.blue.data.repo.database.MandalartRepo
import com.blue.model.Mandalart
import javax.inject.Inject

class InitMandalartUseCase @Inject constructor(
    private val mandalartRepo: MandalartRepo
) {
    suspend operator fun invoke(){
        val list = mutableListOf<Mandalart>()
        repeat(9){ list.add(Mandalart(it.toLong(), 0)) }
        mandalartRepo.insertMandalart(list)
    }
}