package com.tedm.logincompose.feature_auth.domain.models

import com.tedm.logincompose.core.util.SimpleResource
import com.tedm.logincompose.feature_auth.presentation.util.AuthError

data class LoginResult(
    val emailError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: SimpleResource? = null
)
