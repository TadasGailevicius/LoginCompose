package com.tedm.logincompose.feature_auth.data.remote

import com.tedm.logincompose.core.data.dto.response.BasicApiResponse
import com.tedm.logincompose.feature_auth.data.remote.request.LoginRequest
import com.tedm.logincompose.feature_auth.data.remote.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("/credentials")
    suspend fun login(
        @Body request: LoginRequest
    ): BasicApiResponse<AuthResponse>

    @GET("/api/user/authenticate")
    suspend fun authenticate()

    companion object {
        const val BASE_URL = "https://vidqjclbhmef.herokuapp.com/"
    }
}