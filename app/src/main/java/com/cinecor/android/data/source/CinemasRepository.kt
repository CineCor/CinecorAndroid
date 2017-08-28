package com.cinecor.android.data.source

import com.cinecor.android.data.model.Cinema
import com.cinecor.android.data.model.Movie
import io.reactivex.Flowable
import javax.inject.Inject

class CinemasRepository
@Inject constructor(private val localDataSource: CinecorDataSource, private val remoteDataSource: CinecorDataSource)
    : CinecorDataSource {

    override fun getCinemas(): Flowable<List<Cinema>> = Flowable.merge(
            localDataSource.getCinemas(),
            remoteDataSource.getCinemas().doOnNext(localDataSource::saveCinemas)
    ).filter { it.isNotEmpty() }

    override fun getCinema(id: Int): Flowable<Cinema> =
            localDataSource.getCinema(id)

    override fun getMoviesFromCinema(id: Int): Flowable<List<Movie>> =
            localDataSource.getMoviesFromCinema(id)

    override fun getMovieFromCinema(cinemaId: Int, movieId: Int): Flowable<Movie> =
            localDataSource.getMovieFromCinema(cinemaId, movieId)

    override fun saveCinemas(cinemas: List<Cinema>) = localDataSource.saveCinemas(cinemas)

    override fun deleteCinemas() = localDataSource.deleteCinemas()
}
