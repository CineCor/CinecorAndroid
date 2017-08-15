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

    fun getCinemas(callback: LoadCinemasCallback)

    fun getCinema(id: Int, callback: GetCinemaCallback)

    fun getMoviesFromCinema(cinemaId: Int, callback: GetMoviesCallback)

    fun refreshCinemas()

    fun deleteCinemas()
}
