package com.cinecor.android.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "cinemas")
data class Cinema(@PrimaryKey
                  val id: Int = 0,
                  val name: String = "",
                  @Embedded
                  val movies: List<Movie> = ArrayList(),
                  val address: String? = null,
                  val image: String? = null,
                  val rooms: String? = null,
                  val phone: String? = null,
                  val web: String? = null)
