package com.orafaelsc.cstvfuze.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.orafaelsc.cstvfuze.data.remote.MatchesApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

import retrofit2.Retrofit

// This Koin module is responsible for creating all network-related objects
val networkModule = module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer YAqDjdon0cKQkfEPOyZrOB8wneXfznhnek8dpBH8g39nmK_QunU") // todo move to secrets
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    single {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
        }

        Retrofit.Builder()
            .baseUrl("https://api.pandascore.co/")
            .client(get<OkHttpClient>())
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    single {
        get<Retrofit>().create(MatchesApi::class.java)
    }
}

