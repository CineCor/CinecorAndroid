package com.cinecor.android.data.source

import android.arch.lifecycle.LiveData
import com.cinecor.android.data.model.Cinema
import com.cinecor.android.data.model.Movie
import javax.inject.Inject

class CinemasRepository
@Inject constructor(private val localDataSource: CinecorDataSource, private val remoteDataSource: CinecorDataSource)
    : CinecorDataSource {

    override fun getCinemas(callback: CinecorDataSource.LoadCinemasCallback) {
        localDataSource.getCinemas(object : CinecorDataSource.LoadCinemasCallback {
            override fun onCinemasLoaded(cinemas: LiveData<List<Cinema>>) {
                callback.onCinemasLoaded(cinemas)
            }

            override fun onDataNotAvailable() {
                remoteDataSource.getCinemas(object : CinecorDataSource.LoadCinemasCallback {
                    override fun onCinemasLoaded(cinemas: LiveData<List<Cinema>>) {
                        localDataSource.saveCinemas(cinemas.value!!)
                        callback.onCinemasLoaded(cinemas)
                    }

                    override fun onDataNotAvailable() {
                        callback.onDataNotAvailable()
                    }
                })
            }
        })
    }

    override fun getCinema(id: Int, callback: CinecorDataSource.GetCinemaCallback) {
        localDataSource.getCinema(id, object : CinecorDataSource.GetCinemaCallback {
            override fun onCinemaLoaded(cinema: LiveData<Cinema>) {
                callback.onCinemaLoaded(cinema)
            }

            override fun onDataNotAvailable() {
                remoteDataSource.getCinema(id, callback)
            }
        })
    }

    override fun getMoviesFromCinema(id: Int, callback: CinecorDataSource.GetMoviesCallback) {
        localDataSource.getMoviesFromCinema(id, object : CinecorDataSource.GetMoviesCallback {
            override fun onMoviesLoaded(movies: LiveData<List<Movie>>) {
                callback.onMoviesLoaded(movies)
            }

            override fun onDataNotAvailable() {
                remoteDataSource.getMoviesFromCinema(id, callback)
            }

        })
    }

    override fun getMovieFromCinema(cinemaId: Int, movieId: Int, callback: CinecorDataSource.GetMovieCallback) {
        localDataSource.getMovieFromCinema(cinemaId, movieId, object : CinecorDataSource.GetMovieCallback {
            override fun onMovieLoaded(movie: LiveData<Movie>) {
                callback.onMovieLoaded(movie)
            }

            override fun onDataNotAvailable() {
                remoteDataSource.getMovieFromCinema(cinemaId, movieId, callback)
            }
        })
    }

    override fun saveCinemas(cinemas: List<Cinema>) {
        localDataSource.saveCinemas(cinemas)
    }

    override fun deleteCinemas() {
        localDataSource.deleteCinemas()
    }
}
