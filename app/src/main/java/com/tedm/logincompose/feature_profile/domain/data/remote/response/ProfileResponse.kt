package com.tedm.logincompose.feature_profile.domain.data.remote.response

import com.tedm.logincompose.feature_profile.domain.model.Profile

data class ProfileResponse(
    val address: String,
    val firstName: String,
    val image: String,
    val lastName: String,
    val phone: String,
    val uuid: String
) {
    fun toProfile(): Profile {
        return Profile(
            address = address,
            fullName = "$firstName $lastName",
            image = image,
            phone = phone
        )
    }
}