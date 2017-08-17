package com.cinecor.android.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.cinecor.android.data.model.Cinema
import com.cinecor.android.data.model.Movie
import io.reactivex.Flowable

@Dao
interface CinemaDao {

    @Query("SELECT * FROM Cinemas")
    fun getCinemas(): Flowable<List<Cinema>>

    @Query("SELECT * FROM Cinemas WHERE id = :id")
    fun getCinemaById(id: Int): Flowable<Cinema>

    @Query("SELECT movies FROM Cinemas WHERE id = :id")
    fun getMoviesFromCinema(id: Int): Flowable<List<Movie>>

    @Query("SELECT * FROM Movies WHERE id = :movieId") // TODO Improve
    fun getMovieFromCinema(cinemaId: Int, movieId: Int): Flowable<Movie>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCinemas(cinemas: List<Cinema>)


    @Query("DELETE FROM Cinemas")
    fun deleteCinemas()
}
