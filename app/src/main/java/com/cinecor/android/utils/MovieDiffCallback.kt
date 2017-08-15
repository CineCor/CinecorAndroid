package com.cinecor.android.utils

import android.support.v7.util.DiffUtil
import com.cinecor.android.data.model.Movie

class MovieDiffCallback(var oldMovies: List<Movie>, var newMovies: List<Movie>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldMovies.size

    override fun getNewListSize(): Int = newMovies.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldMovies[oldItemPosition].id == newMovies[newItemPosition].id


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldMovies[oldItemPosition] == newMovies[newItemPosition]
}
