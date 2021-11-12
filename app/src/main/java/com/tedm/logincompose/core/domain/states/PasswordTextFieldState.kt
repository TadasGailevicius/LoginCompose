package com.tedm.logincompose.core.domain.states

import com.tedm.logincompose.core.util.Error

data class PasswordTextFieldState(
    val text: String = "",
    val error: Error? = null,
    val isPasswordVisible: Boolean = false
)
