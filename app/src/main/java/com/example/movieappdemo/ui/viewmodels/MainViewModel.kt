package com.example.movieappdemo.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappdemo.pojo.PopularMovies
import com.example.movieappdemo.repos.DataRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val mutableLiveData = MutableLiveData<PopularMovies>()
    val popularMoviesLiveData = mutableLiveData as LiveData<PopularMovies>

    suspend fun getPopularMovies(api_key: String) {
        viewModelScope.launch {
            val response = async { DataRepository.getPopular(api_key) }

            if (response.await().isSuccessful){
                mutableLiveData.value = response.await().body()
            }
        }

    }
}