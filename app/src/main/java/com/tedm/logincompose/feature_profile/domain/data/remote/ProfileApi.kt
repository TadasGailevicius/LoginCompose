package com.tedm.logincompose.feature_profile.domain.data.remote

import com.tedm.logincompose.feature_profile.domain.data.remote.response.ProfileResponse
import retrofit2.http.GET

interface ProfileApi {

    @GET("/user")
    suspend fun getProfile(): ProfileResponse

    companion object {
        const val BASE_URL = "https://vidqjclbhmef.herokuapp.com/"
    }
}