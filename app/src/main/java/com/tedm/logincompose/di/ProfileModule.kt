package com.tedm.logincompose.di

import android.content.SharedPreferences
import com.tedm.logincompose.feature_auth.data.local.UserDao
import com.tedm.logincompose.feature_profile.data.repository.ProfileRepositoryImpl
import com.tedm.logincompose.feature_profile.domain.data.remote.ProfileApi
import com.tedm.logincompose.feature_profile.domain.repository.ProfileRepository
import com.tedm.logincompose.feature_profile.domain.use_case.GetProfileUseCase
import com.tedm.logincompose.feature_profile.domain.use_case.LogoutUseCase
import com.tedm.logincompose.feature_profile.domain.use_case.ProfileUseCases
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
object ProfileModule {
    @Provides
    @Singleton
    fun provideProfileApi(client: OkHttpClient): ProfileApi {
        return Retrofit.Builder()
            .baseUrl(ProfileApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        profileApi: ProfileApi,
        dao: UserDao,
        sharedPreferences: SharedPreferences
    ): ProfileRepository {
        return ProfileRepositoryImpl(profileApi, dao, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(repository: ProfileRepository): ProfileUseCases {
        return ProfileUseCases(
            getProfile = GetProfileUseCase(repository),
            logout = LogoutUseCase(repository)
        )
    }
}