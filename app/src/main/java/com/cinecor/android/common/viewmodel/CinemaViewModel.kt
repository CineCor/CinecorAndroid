package com.cinecor.android.common.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.cinecor.android.common.model.Cinema
import com.cinecor.android.common.model.Movie
import com.cinecor.android.common.repository.CinemasRepository

class CinemaViewModel(repository: CinemasRepository) : ViewModel() {

    private val cinemas = repository.getCinemas()

    fun getCinemas(): LiveData<List<Cinema>> {
        return cinemas
    }

    fun getCinema(cinemaId: Int): LiveData<Cinema> {
        return Transformations.map(cinemas, { it.find { it.id == cinemaId } })
    }

    fun getMovie(movieId: Int): LiveData<Movie> {
        return Transformations.map(cinemas, { it.flatMap { it.movies }.find { it.id == movieId } })
    }

    fun getMovieFromCinema(movieId: Int, cinemaId: Int): LiveData<Movie> {
        return Transformations.map(cinemas, { it.find { it.id == cinemaId }?.movies?.find { it.id == movieId } })
    }
}
