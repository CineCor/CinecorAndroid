package com.cinecor.android.common.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.cinecor.android.data.model.Cinema
import com.cinecor.android.data.model.Movie
import com.cinecor.android.data.source.CinemasRepository

class CinemaViewModel(private val repository: CinemasRepository) : ViewModel() {

    fun getCinemas(): LiveData<List<Cinema>> {
        val cinemas = MutableLiveData<List<Cinema>>()
        repository.getCinemas().doOnNext { cinemas.value = it }
        return cinemas
    }

    fun getCinema(cinemaId: Int): LiveData<Cinema> {
        val cinema = MutableLiveData<Cinema>()
        repository.getCinema(cinemaId).doOnNext { cinema.value = it }
        return cinema
    }

    fun getMovieFromCinema(movieId: Int, cinemaId: Int): LiveData<Movie> {
        val movie = MutableLiveData<Movie>()
        repository.getMovieFromCinema(movieId, cinemaId).doOnNext { movie.value = it }
        return movie
    }
}
