package com.cinecor.android.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.cinecor.android.utils.DateUtils.formatedHour
import com.cinecor.android.utils.DateUtils.isAfterNow

@Entity(tableName = "movies")
data class Movie(@PrimaryKey
                 val id: Int = 0,
                 val title: String = "",
                 val hours: List<String> = ArrayList(),
                 val is3d: Boolean = false,
                 val isVose: Boolean = false,
                 val images: HashMap<String, String> = HashMap(),
                 val colors: HashMap<String, String> = HashMap(),
                 val overview: String = "",
                 val imdb: String? = null,
                 val rating: Float? = null,
                 val duration: Int? = null,
                 val trailer: String? = null,
                 val releaseDate: String? = null,
                 val genres: List<String>? = null,
                 val rawDescription: String? = null) {

    enum class Images {POSTER, POSTER_THUMBNAIL, BACKDROP, BACKDROP_THUMBNAIL }
    enum class Colors {MAIN, TITLE, BODY }

    fun getBackdropImages(): Pair<String?, String?>? {
        if (images.containsKey(Images.BACKDROP.toString())) {
            return Pair(images[Images.BACKDROP.toString()], images[Images.BACKDROP_THUMBNAIL.toString()])
        } else {
            return getPosterImages()
        }
    }

    fun getPosterImages(): Pair<String?, String?>? {
        return Pair(images[Images.POSTER.toString()], images[Images.POSTER_THUMBNAIL.toString()])
    }

    fun getFormattedHours(): String? {
        val fullhours = StringBuffer()
        hours.forEachIndexed { i, hour ->
            if (hour.isAfterNow()) {
                fullhours.append(hour.formatedHour())
                if (i < hours.size - 1) fullhours.append(" - ")
            }
        }
        return fullhours.toString()
    }
}
