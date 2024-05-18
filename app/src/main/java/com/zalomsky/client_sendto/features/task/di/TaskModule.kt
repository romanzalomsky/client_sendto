package com.zalomsky.client_sendto.features.task.di

import com.zalomsky.client_sendto.features.task.data.TaskApi
import com.zalomsky.client_sendto.features.task.data.TaskDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TaskModule {

    @Provides
    fun provideTaskRepository(taskApi: TaskApi): TaskDataSource =
        TaskDataSource(taskApi)

    @Singleton
    @Provides
    fun provideTaskApiService(okHttpClient: OkHttpClient, retrofit: Retrofit.Builder): TaskApi =
        retrofit
            .client(okHttpClient)
            .build()
            .create(TaskApi::class.java)
}