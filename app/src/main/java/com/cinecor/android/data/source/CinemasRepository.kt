package com.cinecor.android.data.source

import android.arch.lifecycle.LiveData
import com.cinecor.android.data.model.Cinema
import javax.inject.Inject

class CinemasRepository
@Inject constructor(private val localDataSource: CinecorDataSource, private val remoteDataSource: CinecorDataSource)
    : CinecorDataSource {

    private var localIsDirty = false

    override fun getCinemas(callback: CinecorDataSource.LoadCinemasCallback) {
        return if (localIsDirty) {
            remoteDataSource.getCinemas(object : CinecorDataSource.LoadCinemasCallback {
                override fun onCinemasLoaded(cinemas: LiveData<List<Cinema>>) {
                    localDataSource.saveCinemas(cinemas.value!!)
                    callback.onCinemasLoaded(cinemas)
                }

                override fun onDataNotAvailable() {
                    callback.onDataNotAvailable()
                }
            })
        } else {
            localDataSource.getCinemas(object : CinecorDataSource.LoadCinemasCallback {
                override fun onCinemasLoaded(cinemas: LiveData<List<Cinema>>) {
                    callback.onCinemasLoaded(cinemas)
                }

                override fun onDataNotAvailable() {
                    remoteDataSource.getCinemas(callback)
                }
            })
        }
    }

    override fun getCinema(id: Int, callback: CinecorDataSource.GetCinemaCallback) {
        return if (localIsDirty) {
            remoteDataSource.getCinema(id, callback)
        } else {
            localDataSource.getCinema(id, callback)
        }
    }

    override fun getMoviesFromCinema(id: Int, callback: CinecorDataSource.GetMoviesCallback) {
        return if (localIsDirty) {
            remoteDataSource.getMoviesFromCinema(id, callback)
        } else {
            localDataSource.getMoviesFromCinema(id, callback)
        }
    }

    override fun getMovieFromCinema(cinemaId: Int, movieId: Int, callback: CinecorDataSource.GetMovieCallback) {
        return if (localIsDirty) {
            remoteDataSource.getMovieFromCinema(cinemaId, movieId, callback)
        } else {
            localDataSource.getMovieFromCinema(cinemaId, movieId, callback)
        }
    }

    override fun saveCinemas(cinemas: List<Cinema>) {
        localDataSource.saveCinemas(cinemas)
    }

    override fun deleteCinemas() {
        localDataSource.deleteCinemas()
    }

    override fun refreshCinemas() {
        localIsDirty = true
    }
}
