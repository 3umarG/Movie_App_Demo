package com.example.movieappdemo.repos

import com.example.movieappdemo.data.RetrofitInstance

class DataRepository {
    companion object {
        suspend fun getPopular(api_key: String) =
            RetrofitInstance.api.getPopular(api_key)
    }
}