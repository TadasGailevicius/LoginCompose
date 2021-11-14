package com.tedm.logincompose.feature_auth.domain.repository

import com.tedm.logincompose.core.util.SimpleResource
import retrofit2.http.GET

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): SimpleResource

    suspend fun authenticate(): SimpleResource
}