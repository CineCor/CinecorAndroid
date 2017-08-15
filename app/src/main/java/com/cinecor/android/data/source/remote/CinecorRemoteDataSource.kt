package com.cinecor.android.data.source.remote

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.cinecor.android.data.model.Cinema
import com.cinecor.android.data.source.CinecorDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn
import javax.inject.Inject

class CinecorRemoteDataSource
@Inject constructor(val firebaseAuth: FirebaseAuth, val database: FirebaseDatabase, val logger: AnkoLogger)
    : CinecorDataSource {

    private var cinemas = MutableLiveData<List<Cinema>>()

    override fun getCinemas(callback: CinecorDataSource.LoadCinemasCallback) {
        firebaseAuth.signInAnonymously().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val reference = database.getReference("cinemas")
                reference.keepSynced(true)
                reference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        cinemas.value = dataSnapshot.children.map { it.getValue(Cinema::class.java)!! }
                        callback.onCinemasLoaded(cinemas)
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        logger.warn { databaseError.message }
                        callback.onDataNotAvailable()
                        cinemas.value = null
                    }
                })
            } else {
                logger.warn { "Authentication failed" }
                callback.onDataNotAvailable()
                cinemas.value = null
            }
        }
    }

    override fun getCinema(id: Int, callback: CinecorDataSource.GetCinemaCallback) {
        cinemas.value?.let {
            callback.onCinemaLoaded(Transformations.map(cinemas, { it.find { it.id == id } }))
        } ?: callback.onDataNotAvailable()
    }

    override fun getMoviesFromCinema(id: Int, callback: CinecorDataSource.GetMoviesCallback) {
        cinemas.value?.let {
            callback.onMoviesLoaded(Transformations.map(cinemas, { it.find { it.id == id }?.movies }))
        } ?: callback.onDataNotAvailable()
    }

    override fun getMovieFromCinema(cinemaId: Int, movieId: Int, callback: CinecorDataSource.GetMovieCallback) {
        cinemas.value?.let {
            callback.onMovieLoaded(Transformations.map(cinemas, { it.find { it.id == cinemaId }?.movies?.find { it.id == movieId } }))
        } ?: callback.onDataNotAvailable()
    }

    override fun saveCinemas(cinemas: List<Cinema>) {} // Not needed here

    override fun refreshCinemas() {} // Not needed here

    override fun deleteCinemas() {} // Not needed here
}
