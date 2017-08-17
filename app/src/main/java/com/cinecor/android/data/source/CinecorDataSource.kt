package com.cinecor.android.data.source

import com.cinecor.android.data.model.Cinema
import com.cinecor.android.data.model.Movie
import io.reactivex.Flowable

interface CinecorDataSource {

    fun getCinemas(): Flowable<List<Cinema>>

    fun getCinema(id: Int): Flowable<Cinema>

    fun getMoviesFromCinema(id: Int): Flowable<List<Movie>>

    fun getMovieFromCinema(cinemaId: Int, movieId: Int): Flowable<Movie>

    fun saveCinemas(cinemas: List<Cinema>)

    fun deleteCinemas()
}
