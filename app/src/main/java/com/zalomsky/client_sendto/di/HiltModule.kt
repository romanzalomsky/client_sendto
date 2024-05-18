package com.zalomsky.client_sendto.di

import com.zalomsky.client_sendto.service.ClientApi
import com.zalomsky.client_sendto.service.UserApi
import com.zalomsky.client_sendto.repository.ClientDataSource
import com.zalomsky.client_sendto.features.task.data.TaskDataSource
import com.zalomsky.client_sendto.repository.UserDataSource
import com.zalomsky.client_sendto.features.task.data.TaskApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {

    @Provides
    fun provideClientRepository(clientApi: ClientApi): ClientDataSource =
        ClientDataSource(clientApi)

    @Provides
    fun provideUserRepository(userApi: UserApi): UserDataSource =
        UserDataSource(userApi)
}