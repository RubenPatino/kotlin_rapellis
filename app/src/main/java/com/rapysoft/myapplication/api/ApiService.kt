package com.rapysoft.myapplication.api

import com.rapysoft.myapplication.models.ModelResponseMovie
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("3/movie/popular")
    fun getMoviePopular(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): Call<ModelResponseMovie>

}


