package com.example.movieappdemo.ui.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieappdemo.R
import com.example.movieappdemo.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {

    private lateinit var movieDetailsBinding: FragmentMovieDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        movieDetailsBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return movieDetailsBinding.root
    }

}