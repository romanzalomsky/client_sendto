package com.zalomsky.client_sendto.di

import com.zalomsky.client_sendto.api.ClientApi
import com.zalomsky.client_sendto.api.ClientRemoteData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltModule {

    @Provides
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://192.168.241.159:8080")
        .build()

    @Provides
    fun provideClientApi(retrofit: Retrofit) : ClientApi = retrofit.create(ClientApi::class.java)

    @Provides
    fun provideClientRemoteData(clientApi: ClientApi) : ClientRemoteData = ClientRemoteData(clientApi)
}