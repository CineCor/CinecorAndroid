package com.cinecor.android.common.model

data class Cinema(val id: Int = 0,
                  val name: String = "",
                  val movies: List<Movie> = ArrayList(),
                  val address: String? = null,
                  val image: String? = null,
                  val rooms: String? = null,
                  val phone: String? = null,
                  val web: String? = null)
