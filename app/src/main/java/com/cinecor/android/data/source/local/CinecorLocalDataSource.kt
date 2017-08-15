package com.cinecor.android.data.source.local

import com.cinecor.android.data.source.CinecorDataSource


class CinecorLocalDataSource
private constructor(val cinemaDao: CinemaDao) : CinecorDataSource {

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

    override fun refreshCinemas() {
        // Repository handles it
    }

    override fun deleteCinemas() {
        cinemaDao.deleteCinemas()
    }

}
