package com.cinecor.android.cinemas.movies.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cinecor.android.R
import com.cinecor.android.common.model.Movie
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.request.ImageRequest
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.*


class MoviesAdapter(val listener: OnMovieClickListener) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var movies: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener { listener.onMovieClicked(movies[viewHolder.adapterPosition]) }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(movie) {
                val images = getBackdropImages()
                itemView.title.text = title
                itemView.hours.text = getFormattedHours()
                itemView.backdrop.controller = Fresco.newDraweeControllerBuilder()
                        .setLowResImageRequest(ImageRequest.fromUri(images?.second))
                        .setImageRequest(ImageRequest.fromUri(images?.first))
                        .setOldController(itemView.backdrop.controller)
                        .build()
            }
        }

    }

    interface OnMovieClickListener {
        fun onMovieClicked(movie: Movie)
    }
}
