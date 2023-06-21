package com.slrp.rmm.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.slrp.rmm.network.ApiDataSource
import com.slrp.rmm.network.ApiService
import com.slrp.rmm.util.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideAuthDataSource(apiService: ApiService) = ApiDataSource(apiService)


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, httpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient)
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

//    @Provides
//    @Singleton
//    fun provideBaseUrl() = BASE_URL
//
//    @Provides
//    @Singleton
//    fun connectionTimeout() = NETWORK_TIMEOUT

    @Provides
    fun getHttpClient (@ApplicationContext appContext: Context): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .build()
                chain.proceed(newRequest)
            }
            .build()
        return okHttpClient
    }


}