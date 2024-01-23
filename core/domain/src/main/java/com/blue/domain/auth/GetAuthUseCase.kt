package com.blue.domain.auth

import com.blue.data.repo.supabase.SupabaseRepo
import io.github.jan.supabase.compose.auth.ComposeAuth
import javax.inject.Inject

class GetAuthUseCase @Inject constructor(
    private val supaRepo: SupabaseRepo
) {
    operator fun invoke(): ComposeAuth =
        supaRepo.getAuth()
}