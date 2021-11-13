package com.tedm.logincompose.feature_auth.data.repository

import android.content.SharedPreferences
import com.tedm.logincompose.core.util.Resource
import com.tedm.logincompose.core.util.SimpleResource
import com.tedm.logincompose.core.util.UiText
import com.tedm.logincompose.feature_auth.data.remote.AuthApi
import com.tedm.logincompose.feature_auth.data.remote.request.LoginRequest
import com.tedm.logincompose.R
import com.tedm.logincompose.core.util.Constants
import com.tedm.logincompose.feature_auth.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl (
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    override suspend fun login(email: String, password: String): SimpleResource {
        val request = LoginRequest(email, password)
        return try {
            val response = api.login(request)
            if(response.successful) {
                response.data?.let { authResponse ->
                    sharedPreferences.edit()
                        .putString(Constants.KEY_JWT_TOKEN, authResponse.token)
                        .apply()
                }
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun authenticate(): SimpleResource {
        return try {
            api.authenticate()
            Resource.Success(Unit)
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

}