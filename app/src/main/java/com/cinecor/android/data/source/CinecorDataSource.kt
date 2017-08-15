package com.cinecor.android.data.source

import android.arch.lifecycle.LiveData
import com.cinecor.android.data.model.Cinema
import com.cinecor.android.data.model.Movie

interface CinecorDataSource {

    interface LoadCinemasCallback {

        fun onCinemasLoaded(cinemas: LiveData<List<Cinema>>)

        fun onDataNotAvailable()
    }

    interface GetCinemaCallback {

        fun onCinemaLoaded(cinema: LiveData<Cinema>)

        fun onDataNotAvailable()
    }

    interface GetMoviesCallback {

        fun onMoviesLoaded(movie: LiveData<List<Movie>>)

        fun onDataNotAvailable()
    }

    interface GetMovieCallback {

        fun onMovieLoaded(movie: LiveData<Movie>)

        fun onDataNotAvailable()
    }

    fun getCinemas(callback: LoadCinemasCallback)

    fun getCinema(id: Int, callback: GetCinemaCallback)

    fun getMoviesFromCinema(id: Int, callback: GetMoviesCallback)

    fun getMovieFromCinema(cinemaId: Int, movieId: Int, callback: GetMovieCallback)

    fun saveCinemas(cinemas: List<Cinema>)

    fun refreshCinemas()

    fun deleteCinemas()
}
