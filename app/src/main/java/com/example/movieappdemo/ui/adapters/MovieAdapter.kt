package com.example.movieappdemo.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieappdemo.R
import com.example.movieappdemo.pojo.Result
import com.example.movieappdemo.utils.Constants
import com.squareup.picasso.Picasso
import kotlin.coroutines.coroutineContext

class MovieAdapter(
    private val moviesList: List<Result>,
    private val context: Context,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        private val movieTitle = v.findViewById<TextView>(R.id.movieTitle)
        private val movieImage = v.findViewById<ImageView>(R.id.movieImage)
        private val movieOverView = v.findViewById<TextView>(R.id.movieOverView)

        fun bind(result: Result) {
            movieTitle.text = result.title
            movieOverView.text = result.overview
            Picasso
                .get()
                .load(Constants.IMAGE_BASE_URL + result.poster_path)
                .placeholder(R.drawable.progress_animation)
                .into(movieImage)
            val animation = AnimationUtils.loadAnimation(v.context, android.R.anim.slide_in_left)
            v.startAnimation(animation)
        }

        fun click(result: Result) {
            itemView.setOnClickListener {
                listener.onItemClick(result)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_item_rv, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position])

        holder.click(moviesList[position])
    }

    override fun getItemCount() = moviesList.size
}