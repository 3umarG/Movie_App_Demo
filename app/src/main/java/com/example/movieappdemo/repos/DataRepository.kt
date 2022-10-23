package com.example.movieappdemo.repos

import com.example.movieappdemo.data.RetrofitInstance

class DataRepository {
    companion object {
        suspend fun getPopular(api_key: String) =
            RetrofitInstance.api.getPopular(api_key)

        suspend fun getMovieDetails(movie_id: Int, api_key: String) =
            RetrofitInstance.api.getMovieDetails(movie_id, api_key)
    }
}