package com.blue.domain.database.mandalart

import com.blue.data.repo.database.MandalartRepo
import javax.inject.Inject

class DeleteAllMandalartUseCase @Inject constructor(
    private val mandalartRepo: MandalartRepo
){
    suspend operator fun invoke(){
        mandalartRepo.deleteAllMandalart()
    }
}