package com.cinecor.android.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Cinema(@PrimaryKey
                  val id: Int,
                  val name: String,
                  val movies: List<Movie>,
                  val address: String?,
                  val image: String?,
                  val rooms: String?,
                  val phone: String?,
                  val web: String?)
