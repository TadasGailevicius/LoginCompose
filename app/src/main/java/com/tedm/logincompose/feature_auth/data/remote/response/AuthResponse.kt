package com.tedm.logincompose.feature_auth.data.remote.response

data class AuthResponse(
    val token: String,
    val refreshToken: String
)
