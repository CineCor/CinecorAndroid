package com.cinecor.android.common.model

data class Cinema(var id: Int? = 0,
                  var name: String? = null,
                  var image: String? = null,
                  var address: String? = null,
                  var rooms: String? = null,
                  var phone: String? = null,
                  var web: String? = null,
                  var movies: List<Movie>? = null)
