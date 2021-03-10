package com.mj.kmjhomework.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mj.kmjhomework.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<Gson> { GsonBuilder().create() }

    single {
        OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            })
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.punkapi.com/v2/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(
            RetrofitInterface::class.java
        )
    }

}