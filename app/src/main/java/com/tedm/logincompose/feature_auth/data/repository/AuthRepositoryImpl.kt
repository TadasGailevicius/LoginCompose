package com.tedm.logincompose.feature_auth.data.repository

import android.content.SharedPreferences
import com.tedm.logincompose.core.util.Resource
import com.tedm.logincompose.core.util.SimpleResource
import com.tedm.logincompose.core.util.UiText
import com.tedm.logincompose.feature_auth.data.remote.AuthApi
import com.tedm.logincompose.feature_auth.data.remote.request.LoginRequest
import com.tedm.logincompose.R
import com.tedm.logincompose.core.util.Constants
import com.tedm.logincompose.feature_auth.data.local.UserDao
import com.tedm.logincompose.feature_auth.data.local.entities.User
import com.tedm.logincompose.feature_auth.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val dao: UserDao,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {
    override suspend fun login(username: String, password: String): SimpleResource {
        val request = LoginRequest(
            username = username,
            password = password
        )
        val response = try {
            api.login(request)
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
        dao.insertUser(
            User(
                username = username,
                password = password,
                id = 0
            )
        )
        sharedPreferences.edit()
            .putString(Constants.KEY_JWT_TOKEN, response.token)
            .apply()
        return Resource.Success(Unit)
    }

    override suspend fun authenticate(): SimpleResource {
        val user = dao.getUserById(0)
        if (user != null) {
            return try {
                api.login(
                    LoginRequest(
                        user.username,
                        user.password
                    )
                )
                Resource.Success(Unit)
            } catch (e: IOException) {
                Resource.Error(
                    uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
                )
            } catch (e: HttpException) {
                Resource.Error(
                    uiText = UiText.StringResource(R.string.oops_something_went_wrong)
                )
            }
        } else {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }
}