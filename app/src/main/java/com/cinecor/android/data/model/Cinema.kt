package com.cinecor.android.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
data class Cinema(@PrimaryKey
                  var id: Int = 0,
                  var name: String = "",
                  var movies: List<Movie> = ArrayList(),
                  var address: String? = null,
                  var image: String? = null,
                  var rooms: String? = null,
                  var phone: String? = null,
                  var web: String? = null) {

    @Ignore constructor() : this(0, "", ArrayList(), null, null, null, null, null)
}
