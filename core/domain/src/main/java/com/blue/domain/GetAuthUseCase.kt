package com.blue.domain

import com.blue.data.SupaBaseRepo
import io.github.jan.supabase.compose.auth.ComposeAuth
import javax.inject.Inject

class GetAuthUseCase @Inject constructor(
    private val supaRepo: SupaBaseRepo
) {
    operator fun invoke(): ComposeAuth =
        supaRepo.getAuth()
}