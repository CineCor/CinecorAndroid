package com.cinecor.android.data.source.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.cinecor.android.data.model.Cinema
import com.cinecor.android.data.model.Movie

@Dao
interface CinemaDao {

    @Query("SELECT * FROM Cinemas")
    fun getCinemas(): LiveData<List<Cinema>>

    @Query("SELECT * FROM Cinemas WHERE id = :id")
    fun getCinemaById(id: Int): LiveData<Cinema>

    @Query("SELECT movies FROM Cinemas WHERE id = :id")
    fun getMoviesFromCinema(id: Int): LiveData<List<Movie>>

    @Query("SELECT * FROM Movies WHERE id = :movieId") // TODO Improve
    fun getMovieFromCinema(cinemaId: Int, movieId: Int): LiveData<Movie>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCinemas(cinemas: List<Cinema>)


    @Query("DELETE FROM Cinemas")
    fun deleteCinemas()
}
