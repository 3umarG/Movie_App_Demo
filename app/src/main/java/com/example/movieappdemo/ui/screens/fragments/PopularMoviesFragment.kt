package com.example.movieappdemo.ui.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappdemo.R
import com.example.movieappdemo.databinding.FragmentPopularMoviesBinding
import com.example.movieappdemo.ui.adapters.MovieAdapter
import com.example.movieappdemo.ui.viewmodels.MainViewModel
import com.example.movieappdemo.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularMoviesFragment : Fragment() {

    private lateinit var popularMoviesBinding: FragmentPopularMoviesBinding
    private lateinit var movieViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        popularMoviesBinding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        movieViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        return popularMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            val job = launch {
                movieViewModel.getPopularMovies(Constants.API_KEY)
            }

            job.join()
            withContext(Dispatchers.Main) {
                movieViewModel.popularMoviesLiveData.observe(
                    viewLifecycleOwner,
                    Observer { movies ->
                        if (movies != null) {
                            val rvAdapter = MovieAdapter(movies.results, requireContext())
                            popularMoviesBinding.recyclerView.apply {
                                adapter = rvAdapter
                                layoutManager = LinearLayoutManager(context)
                            }

                            popularMoviesBinding.recyclerView.visibility = View.VISIBLE
                            popularMoviesBinding.progressBar.visibility = View.INVISIBLE
                        }
                    })

            }
        }
    }

}