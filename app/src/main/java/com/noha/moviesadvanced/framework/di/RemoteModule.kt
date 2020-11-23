package com.noha.moviesadvanced.framework.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.noha.moviesadvanced.core.data.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author : Abdel-Rahman El-Shikh on Sun , 11/22/2020
 */
@Module
class RemoteModule {
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    fun provideOkHttp(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.networkInterceptors().add(Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.header("Accept", "application/json")
            chain.proceed(requestBuilder.build())
        })
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logger)
            .build()
        return httpClient.build()
    }

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Provides
    fun provideApiService(retrofit: Retrofit.Builder): ApiService = retrofit
        .build()
        .create(ApiService::class.java)
}
