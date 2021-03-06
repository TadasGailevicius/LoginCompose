package com.tedm.logincompose.feature_auth.data.remote

import com.tedm.logincompose.feature_auth.data.remote.request.LoginRequest
import com.tedm.logincompose.feature_auth.data.remote.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/credentials")
    suspend fun login(
        @Body request: LoginRequest
    ): AuthResponse

    companion object {
        const val BASE_URL = "https://vidqjclbhmef.herokuapp.com/"
    }
}