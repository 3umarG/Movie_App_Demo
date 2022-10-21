package com.example.movieappdemo.data

import com.example.movieappdemo.pojo.PopularMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("popular")
    suspend fun getPopular(
        @Query("api_key") api_key : String
    ) : Response<PopularMovies>
}