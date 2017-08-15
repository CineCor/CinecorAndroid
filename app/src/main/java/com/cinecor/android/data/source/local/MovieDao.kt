package com.cinecor.android.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.cinecor.android.data.model.Movie
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movies WHERE id = :id")
    fun getMovieById(id: Int): Flowable<Movie>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)


    @Query("DELETE FROM Movies")
    fun deleteMovies()
}
