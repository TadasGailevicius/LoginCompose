package com.tedm.logincompose.feature_profile.domain.repository

import com.tedm.logincompose.core.util.Resource
import com.tedm.logincompose.feature_profile.domain.model.Profile

interface ProfileRepository {

    suspend fun getProfile(): Resource<Profile>

    fun logout()

    suspend fun deleteUser()
}