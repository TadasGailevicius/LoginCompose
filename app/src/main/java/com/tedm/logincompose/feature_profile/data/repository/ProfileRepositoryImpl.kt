package com.tedm.logincompose.feature_profile.data.repository

import android.content.SharedPreferences
import com.tedm.logincompose.core.util.Resource
import com.tedm.logincompose.feature_profile.domain.data.remote.ProfileApi
import com.tedm.logincompose.feature_profile.domain.model.Profile
import com.tedm.logincompose.feature_profile.domain.repository.ProfileRepository
import com.tedm.logincompose.R
import com.tedm.logincompose.core.util.Constants
import com.tedm.logincompose.core.util.UiText
import com.tedm.logincompose.feature_auth.data.local.UserDao
import retrofit2.HttpException
import java.io.IOException

class ProfileRepositoryImpl(
    private val profileApi: ProfileApi,
    private val dao: UserDao,
    private val sharedPreferences: SharedPreferences
) : ProfileRepository {

    override suspend fun getProfile(): Resource<Profile> {
        val response = try {
            profileApi.getProfile()
        } catch (e: Exception) {
            return Resource.Error(UiText.StringResource(R.string.error_unknown))
        } catch (e: IOException) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
        return Resource.Success(response.toProfile())
    }

    override fun logout() {
        sharedPreferences.edit()
            .remove(Constants.KEY_JWT_TOKEN)
            .apply()
    }

    override suspend fun deleteUser() {
        dao.deleteUserById(0)
    }
}
