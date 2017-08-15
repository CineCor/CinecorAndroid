package com.cinecor.android.data.source.local

import com.cinecor.android.data.source.CinecorDataSource
import javax.inject.Inject

class CinecorLocalDataSource
@Inject constructor(val cinemaDao: CinemaDao) : CinecorDataSource {
    override fun getCinemas(callback: CinecorDataSource.LoadCinemasCallback) {
        val cinemas = cinemaDao.getCinemas()
        cinemas.value?.let { callback.onCinemasLoaded(cinemas) } ?: callback.onDataNotAvailable()
    }

    override fun getCinema(id: Int, callback: CinecorDataSource.GetCinemaCallback) {
        val cinema = cinemaDao.getCinemaById(id)
        cinema.value?.let { callback.onCinemaLoaded(cinema) } ?: callback.onDataNotAvailable()
    }

    override fun getMoviesFromCinema(cinemaId: Int, callback: CinecorDataSource.GetMoviesCallback) {
        val movies = cinemaDao.getMoviesFromCinema(cinemaId)
        movies.value?.let { callback.onMoviesLoaded(movies) } ?: callback.onDataNotAvailable()
    }

    override fun getMovieFromCinema(cinemaId: Int, movieId: Int, callback: CinecorDataSource.GetMovieCallback) {

    }

    override fun refreshCinemas() {
        // Not needed here
    }

    override fun deleteCinemas() {
        cinemaDao.deleteCinemas()
    }
}
