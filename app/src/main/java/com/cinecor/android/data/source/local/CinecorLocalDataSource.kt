package com.cinecor.android.data.source.local

import com.cinecor.android.data.model.Cinema
import com.cinecor.android.data.model.Movie
import com.cinecor.android.data.source.CinecorDataSource
import io.reactivex.Flowable
import javax.inject.Inject

class CinecorLocalDataSource
@Inject constructor(private val cinemaDao: CinemaDao) : CinecorDataSource {

    override fun getCinemas(): Flowable<List<Cinema>> =
            cinemaDao.getCinemas()

    override fun getCinema(id: Int): Flowable<Cinema> =
            cinemaDao.getCinemaById(id)

    override fun getMoviesFromCinema(id: Int): Flowable<List<Movie>> =
            getCinema(id).map { it.movies }

    override fun getMovieFromCinema(cinemaId: Int, movieId: Int): Flowable<Movie> =
            getMoviesFromCinema(cinemaId).map { it.find { it.id == movieId } }

    override fun saveCinemas(cinemas: List<Cinema>) =
            cinemaDao.insertCinemas(cinemas)

    override fun deleteCinemas() =
            cinemaDao.deleteCinemas()
}
