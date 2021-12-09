package com.rapysoft.myapplication.api

import com.rapysoft.myapplication.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiAdapter {

    private var API_SERVICE: ApiService? = null
    val apiService: ApiService?
        get() {
            val httpClient = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                httpClient.addInterceptor(logging)
                httpClient.readTimeout(30,TimeUnit.SECONDS)
                httpClient.connectTimeout(30,TimeUnit.SECONDS)
                logging.level = HttpLoggingInterceptor.Level.BODY
            }

            if (API_SERVICE == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
                API_SERVICE = retrofit.create(ApiService::class.java)
            }
            return API_SERVICE
        }
}

