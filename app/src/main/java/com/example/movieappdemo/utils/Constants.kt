package com.example.movieappdemo.utils

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController

object Constants {
    const val API_KEY = "3189403c43a8d647355f0fcf96627ca1"
    const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"

    // Base URL: https://api.themoviedb.org/3/movie/
    // End Point: popular
    // Query :  -?-  api_key=3189403c43a8d647355f0fcf96627ca1


//    https://api.themoviedb.org/3/movie/
//    End Point : {movie_id}
//    ?api_key=3189403c43a8d647355f0fcf96627ca1
}

fun NavController.safeNavigate(
    @IdRes currentDestinationId: Int,
    @IdRes id: Int,
    args: Bundle? = null
) {
    if (currentDestinationId == currentDestination?.id) {
        navigate(id, args)
    }
}