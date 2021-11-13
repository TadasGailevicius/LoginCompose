package com.tedm.logincompose.di

import com.tedm.logincompose.feature_auth.domain.use_case.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideLoginUseCase(): LoginUseCase {
        return LoginUseCase()
    }
}