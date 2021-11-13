package com.tedm.logincompose.feature_auth.domain.repository

import com.tedm.logincompose.core.util.SimpleResource

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): SimpleResource
}