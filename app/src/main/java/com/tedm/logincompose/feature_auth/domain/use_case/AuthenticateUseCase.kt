package com.tedm.logincompose.feature_auth.domain.use_case

import com.tedm.logincompose.core.util.SimpleResource
import com.tedm.logincompose.feature_auth.domain.repository.AuthRepository

class AuthenticateUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): SimpleResource {
        return repository.authenticate()
    }
}