package com.example.movieappdemo.data

import com.example.movieappdemo.pojo.MovieDetailsModel
import com.example.movieappdemo.pojo.PopularMovies
import com.example.movieappdemo.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("popular")
    suspend fun getPopular(
        @Query("api_key") api_key : String
    ) : Response<PopularMovies>

    @GET("{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") api_key : String = Constants.API_KEY
    ) : Response<MovieDetailsModel>
}