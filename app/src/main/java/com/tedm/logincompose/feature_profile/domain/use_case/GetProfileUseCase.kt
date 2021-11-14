package com.tedm.logincompose.feature_profile.domain.use_case

import com.tedm.logincompose.core.util.Resource
import com.tedm.logincompose.feature_profile.domain.model.Profile
import com.tedm.logincompose.feature_profile.domain.repository.ProfileRepository

class GetProfileUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(): Resource<Profile> {
        return repository.getProfile()
    }
}