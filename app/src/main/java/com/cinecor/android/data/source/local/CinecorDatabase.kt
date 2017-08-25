package com.cinecor.android.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.cinecor.android.data.model.Cinema

@Database(entities = arrayOf(Cinema::class), version = 1)
@TypeConverters(RoomTypeConverter::class)
abstract class CinecorDatabase : RoomDatabase() {

    abstract fun cinemaDao(): CinemaDao

    companion object {
        fun buildDatabase(context: Context) =
                Room.databaseBuilder(context,
                        CinecorDatabase::class.java, "Cinecor.db")
                        .build()
    }
}
