package com.cinecor.android.data.source.remote

import com.cinecor.android.data.model.Cinema
import com.cinecor.android.data.model.Movie
import com.cinecor.android.data.source.CinecorDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Flowable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn
import javax.inject.Inject

class CinecorRemoteDataSource
@Inject constructor(private val firebaseAuth: FirebaseAuth, private val database: FirebaseDatabase, private val logger: AnkoLogger)
    : CinecorDataSource {

    private var cinemas: Flowable<List<Cinema>> = Flowable.never()

    override fun getCinemas(): Flowable<List<Cinema>> {
        firebaseAuth.signInAnonymously().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val reference = database.getReference("cinemas")
                reference.keepSynced(true)
                reference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        cinemas = Flowable.fromArray(dataSnapshot.children.map { it.getValue(Cinema::class.java)!! })
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        logger.warn { databaseError.message }
                        cinemas = Flowable.error(Throwable(databaseError.message))
                    }
                })
            } else {
                logger.warn { "Authentication failed" }
                cinemas = Flowable.error(Throwable("Authentication failed"))
            }
        }
        return cinemas
    }

    override fun getCinema(id: Int): Flowable<Cinema> =
            cinemas.map { it.find { it.id == id } }

    override fun getMoviesFromCinema(id: Int): Flowable<List<Movie>> =
            cinemas.map { it.find { it.id == id }?.movies }

    override fun getMovieFromCinema(cinemaId: Int, movieId: Int): Flowable<Movie> =
            cinemas.map { it.find { it.id == cinemaId }?.movies?.find { it.id == movieId } }

    override fun saveCinemas(cinemas: List<Cinema>) {} // Not needed here

    override fun deleteCinemas() {} // Not needed here
}
