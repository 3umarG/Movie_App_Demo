package com.example.movieappdemo.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappdemo.pojo.MovieDetailsModel
import com.example.movieappdemo.pojo.PopularMovies
import com.example.movieappdemo.repos.DataRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val mutableLiveData = MutableLiveData<PopularMovies>()
    val popularMoviesLiveData = mutableLiveData as LiveData<PopularMovies>

    private val mutableMovieDetails = MutableLiveData<MovieDetailsModel>()
    val movieDetails = mutableMovieDetails as LiveData<MovieDetailsModel>

    suspend fun getPopularMovies(api_key: String) {
        viewModelScope.launch {
            val response = async { DataRepository.getPopular(api_key) }

            if (response.await().isSuccessful) {
                mutableLiveData.value = response.await().body()
            }
        }

    }

    suspend fun getMovieDetails(api_key: String, movie_id: Int) {
        viewModelScope.launch {
            val response = async { DataRepository.getMovieDetails(movie_id, api_key) }

            if (response.await().isSuccessful){
                mutableMovieDetails.value = response.await().body()
            }
        }
    }


}