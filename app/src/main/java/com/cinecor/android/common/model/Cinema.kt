package com.cinecor.android.common.model

data class Cinema(val id: Int = 0,
                  var name: String = "",
                  var movies: List<Movie> = ArrayList(),
                  var address: String? = null,
                  var image: String? = null,
                  var rooms: String? = null,
                  var phone: String? = null,
                  var web: String? = null)
