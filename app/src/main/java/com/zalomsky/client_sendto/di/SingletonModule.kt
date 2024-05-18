package com.zalomsky.client_sendto.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.zalomsky.client_sendto.service.ClientApi
import com.zalomsky.client_sendto.features.task.data.TaskApi
import com.zalomsky.client_sendto.service.UserApi
import com.zalomsky.client_sendto.utils.PreferenceManager
import com.zalomsky.client_sendto.utils.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager = TokenManager(context)

    @Singleton
    @Provides
    fun providePreferenceManager(@ApplicationContext context: Context): PreferenceManager =
        PreferenceManager(context)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        preferenceManager: PreferenceManager
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val tokenInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val token = preferenceManager.getToken()

            if (token != null) {
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
                chain.proceed(newRequest)
            } else {
                chain.proceed(originalRequest)
            }
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(tokenInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl("http://192.168.47.159:8080")
            .addConverterFactory(GsonConverterFactory.create())

    @Singleton
    @Provides
    fun provideUserApiService(retrofit: Retrofit.Builder): UserApi =
        retrofit
            .build()
            .create(UserApi::class.java)

    @Singleton
    @Provides
    fun provideClientApiService(okHttpClient: OkHttpClient, retrofit: Retrofit.Builder): ClientApi =
        retrofit
            .client(okHttpClient)
            .build()
            .create(ClientApi::class.java)
}