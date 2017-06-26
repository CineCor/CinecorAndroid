package com.cinecor.android.common.model

import com.cinecor.android.utils.DateUtils.formatedHour
import com.cinecor.android.utils.DateUtils.isAfterNow

data class Movie(val id: Int = 0,
                 var title: String = "",
                 var hours: List<String> = ArrayList(),
                 var is3d: Boolean = false,
                 var isVose: Boolean = false,
                 var images: HashMap<String, String> = HashMap(),
                 var colors: HashMap<String, String> = HashMap(),
                 var overview: String = "",
                 var imdb: String? = null,
                 var rating: Float? = null,
                 var duration: Int? = null,
                 var trailer: String? = null,
                 var releaseDate: String? = null,
                 var genres: List<String>? = null,
                 var rawDescription: String? = null) {

    enum class Images {POSTER, POSTER_THUMBNAIL, BACKDROP, BACKDROP_THUMBNAIL }
    enum class Colors {MAIN, TITLE, BODY }

    fun getBackdropImages(): Pair<String?, String?>? {
        val main: String?
        val thumbnail: String?
        if (images.containsKey(Images.BACKDROP.toString())) {
            main = images[Images.BACKDROP.toString()]
            thumbnail = images[Images.BACKDROP_THUMBNAIL.toString()]
        } else {
            main = images[Images.POSTER.toString()]
            thumbnail = images[Images.POSTER_THUMBNAIL.toString()]
        }
        return Pair(main, thumbnail)
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
