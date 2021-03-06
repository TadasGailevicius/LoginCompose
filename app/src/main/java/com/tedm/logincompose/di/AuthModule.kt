package com.tedm.logincompose.di

import android.content.SharedPreferences
import com.tedm.logincompose.feature_auth.data.local.UserDao
import com.tedm.logincompose.feature_auth.data.remote.AuthApi
import com.tedm.logincompose.feature_auth.data.repository.AuthRepositoryImpl
import com.tedm.logincompose.feature_auth.domain.repository.AuthRepository
import com.tedm.logincompose.feature_auth.domain.use_case.AuthenticateUseCase
import com.tedm.logincompose.feature_auth.domain.use_case.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideAuthApi(client: OkHttpClient): AuthApi {
        return Retrofit.Builder()
            .baseUrl(AuthApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthApi,
        dao: UserDao,
        sharedPreferences: SharedPreferences
    ): AuthRepository {
        return AuthRepositoryImpl(api, dao, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAuthenticationUseCase(repository: AuthRepository): AuthenticateUseCase {
        return AuthenticateUseCase(repository)
    }
}