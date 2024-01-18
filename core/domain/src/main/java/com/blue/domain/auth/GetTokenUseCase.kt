package com.blue.domain.auth

import com.blue.data.repo.supabase.SupabaseRepoImpl
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val repo: SupabaseRepoImpl
) {
    operator fun invoke(): String? = repo.getToken()
}