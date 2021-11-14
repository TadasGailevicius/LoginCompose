package com.tedm.logincompose.feature_auth.domain.use_case

import com.tedm.logincompose.feature_auth.domain.models.LoginResult
import com.tedm.logincompose.feature_auth.domain.repository.AuthRepository
import com.tedm.logincompose.feature_auth.presentation.util.AuthError

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): LoginResult {
        val emailError = when {
            username.isBlank() -> {
                AuthError.FieldEmpty
            }
            username.length < 3 -> {
                AuthError.InputTooShort
            }
            else -> null
        }
        val passwordError =
            when {
                password.isBlank() -> {
                    AuthError.FieldEmpty
                }
                password.length < 3 -> {
                    AuthError.InputTooShort
                }
                else -> null
            }

        if (emailError != null || passwordError != null) {
            return LoginResult(emailError, passwordError)
        }

        return LoginResult(
            result = repository.login(username, password)
        )
    }
}