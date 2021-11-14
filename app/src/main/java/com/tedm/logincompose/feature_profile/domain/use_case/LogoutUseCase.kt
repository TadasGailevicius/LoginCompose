package com.tedm.logincompose.feature_profile.domain.use_case

import com.tedm.logincompose.feature_profile.domain.repository.ProfileRepository

class LogoutUseCase(
    private val repository: ProfileRepository
) {

    operator fun invoke() {
        repository.logout()
    }
}