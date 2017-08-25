package com.cinecor.android.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.cinecor.android.data.model.Cinema
import io.reactivex.Flowable

@Dao
interface CinemaDao {

    @Query("SELECT * FROM cinema")
    fun getCinemas(): Flowable<List<Cinema>>

    @Query("SELECT * FROM cinema WHERE id = :id LIMIT 1")
    fun getCinemaById(id: Int): Flowable<Cinema>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCinemas(cinemas: List<Cinema>)


    @Query("DELETE FROM cinema")
    fun deleteCinemas()
}
