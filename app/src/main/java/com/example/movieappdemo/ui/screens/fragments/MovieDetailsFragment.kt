package com.example.movieappdemo.ui.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieappdemo.R
import com.example.movieappdemo.databinding.FragmentMovieDetailsBinding
import com.example.movieappdemo.ui.viewmodels.MainViewModel
import com.example.movieappdemo.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsFragment : Fragment() {


    private lateinit var movieDetailsBinding: FragmentMovieDetailsBinding
    private lateinit var movieViewModel: MainViewModel
    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        movieDetailsBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        movieViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        return movieDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launch(Dispatchers.IO) {
            val job = launch {
                movieViewModel.getMovieDetails(Constants.API_KEY, args.movieId)
            }

            job.join()
            withContext(Dispatchers.Main) {
                movieViewModel.movieDetails.observe(
                    viewLifecycleOwner,
                    Observer { movieDetails ->

                        if (movieDetails != null) {
                            val vote = "Vote : ${movieDetails.vote_average.toString()}"
                            movieDetailsBinding.tvMovieDetailsVote.text = vote

                            val title = "Title : ${movieDetails.title}"
                            movieDetailsBinding.tvMovieDetailsTitle.text = title

                            val overView = "Over View : ${movieDetails.overview}"
                            movieDetailsBinding.tvMovieDetailsOverView.text = overView

                            val adult = if (movieDetails.adult) "Adult : Yes" else "Adult : No"
                            movieDetailsBinding.tvMovieDetailsAdultOrNot.text = adult

                            Picasso
                                .get()
                                .load(Constants.IMAGE_BASE_URL + args.posterPath)
                                .placeholder(R.drawable.progress_animation)
                                .into(movieDetailsBinding.ivMovieDetailsImage)

                            movieDetailsBinding.progressBar.visibility = View.INVISIBLE
                            movieDetailsBinding.info.visibility = View.VISIBLE
                        }


                    }
                )

            }
        }

    }

}