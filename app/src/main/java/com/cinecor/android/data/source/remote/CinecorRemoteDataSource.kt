package com.cinecor.android.data.source.remote

import com.cinecor.android.data.model.Cinema
import com.cinecor.android.data.model.Movie
import com.cinecor.android.data.source.CinecorDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseAuth
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Flowable
import javax.inject.Inject

class CinecorRemoteDataSource
@Inject constructor(private val firebaseAuth: FirebaseAuth, private val database: FirebaseDatabase)
    : CinecorDataSource {

    private var cinemas = RxFirebaseAuth.signInAnonymously(firebaseAuth).toFlowable().flatMap {
        RxFirebaseDatabase.observeValueEvent(database.getReference("cinemas"), DataSnapshotMapper.listOf<Cinema>(Cinema::class.java))
    }

    override fun getCinemas(): Flowable<List<Cinema>> =
            cinemas

    override fun getCinema(id: Int): Flowable<Cinema> =
            cinemas.map { it.find { it.id == id } }

    override fun getMoviesFromCinema(id: Int): Flowable<List<Movie>> =
            cinemas.map { it.find { it.id == id }?.movies }

    override fun getMovieFromCinema(cinemaId: Int, movieId: Int): Flowable<Movie> =
            cinemas.map { it.find { it.id == cinemaId }?.movies?.find { it.id == movieId } }

    override fun saveCinemas(cinemas: List<Cinema>) {} // Not needed here

    override fun deleteCinemas() {} // Not needed here
}
