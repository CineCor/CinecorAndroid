package com.cinecor.android.data.source

import javax.inject.Inject

class CinemasRepository
@Inject constructor(private val localDataSource: CinecorDataSource, private val remoteDataSource: CinecorDataSource)
    : CinecorDataSource {

    override fun getCinemas(callback: CinecorDataSource.LoadCinemasCallback) {

    }

    override fun getCinema(id: Int, callback: CinecorDataSource.GetCinemaCallback) {

    }

    override fun getMoviesFromCinema(cinemaId: Int, callback: CinecorDataSource.GetMoviesCallback) {

    }

    override fun getMovieFromCinema(cinemaId: Int, movieId: Int, callback: CinecorDataSource.GetMovieCallback) {

    }

    override fun refreshCinemas() {

    }

    override fun deleteCinemas() {

    }
}
