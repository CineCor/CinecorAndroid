package com.cinecor.android.common.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import com.cinecor.android.data.model.Cinema
import com.cinecor.android.data.model.Movie
import com.cinecor.android.data.source.CinemasRepository

class CinemaViewModel(private val repository: CinemasRepository) : ViewModel() {

    fun getCinemas(): LiveData<List<Cinema>> =
            LiveDataReactiveStreams.fromPublisher(repository.getCinemas())

    fun getCinema(cinemaId: Int): LiveData<Cinema> =
            LiveDataReactiveStreams.fromPublisher(repository.getCinema(cinemaId))

    fun getMovieFromCinema(movieId: Int, cinemaId: Int): LiveData<Movie> =
            LiveDataReactiveStreams.fromPublisher(repository.getMovieFromCinema(movieId, cinemaId))
}
