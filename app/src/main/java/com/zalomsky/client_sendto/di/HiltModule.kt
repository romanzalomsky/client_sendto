package com.zalomsky.client_sendto.di

import com.zalomsky.client_sendto.service.ClientApiService
import com.zalomsky.client_sendto.service.UserApiService
import com.zalomsky.client_sendto.repository.ClientRepository
import com.zalomsky.client_sendto.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {

    @Provides
    fun provideClientRepository(clientApiService: ClientApiService): ClientRepository =
        ClientRepository(clientApiService)

    @Provides
    fun provideUserRepository(userApiService: UserApiService): UserRepository =
        UserRepository(userApiService)
}