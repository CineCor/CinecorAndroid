package com.cinecor.android.cinemas.movies.ui

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cinecor.android.R
import com.cinecor.android.data.model.Movie
import com.cinecor.android.utils.MovieDiffCallback
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.*


class MoviesAdapter(val listener: OnMovieClickListener) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var movies: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener { listener.onMovieClicked(movies[viewHolder.adapterPosition], view.backdrop) }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateMovies(movies: List<Movie>) {
        DiffUtil.calculateDiff(MovieDiffCallback(this.movies, movies)).dispatchUpdatesTo(this)
        this.movies = movies
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(movie) {
                val images = getBackdropImages()
                itemView.title.text = title
                itemView.hours.text = getFormattedHours()
                Glide.with(itemView)
                        .load(images?.first)
                        .thumbnail(Glide.with(itemView).load(images?.second))
                        .into(itemView.backdrop)
            }
        }

    }

    interface OnMovieClickListener {
        fun onMovieClicked(movie: Movie, image: ImageView)
    }
}
