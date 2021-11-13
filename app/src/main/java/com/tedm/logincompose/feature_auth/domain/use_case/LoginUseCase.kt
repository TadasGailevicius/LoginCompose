package com.tedm.logincompose.feature_auth.domain.use_case

import com.tedm.logincompose.core.util.SimpleResource
import com.tedm.logincompose.feature_auth.domain.models.LoginResult
import com.tedm.logincompose.feature_auth.domain.repository.AuthRepository
import com.tedm.logincompose.feature_auth.presentation.util.AuthError

class LoginUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): LoginResult {
        val emailError = if(email.isBlank()) AuthError.FieldEmpty else null
        val passwordError = if(password.isBlank()) AuthError.FieldEmpty else null

        if(emailError != null || passwordError != null) {
            return LoginResult(emailError, passwordError)
        }

        return LoginResult(
            result = repository.login(email, password)
        )
    }
}