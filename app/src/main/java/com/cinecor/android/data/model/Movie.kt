package com.cinecor.android.data.model

import com.cinecor.android.utils.DateUtils.formatedHour
import com.cinecor.android.utils.DateUtils.isAfterNow

data class Movie(var id: Int = 0,
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
                 var rawDescription: String? = null,
                 var url: String? = null) {

    enum class Images {POSTER, POSTER_THUMBNAIL, BACKDROP, BACKDROP_THUMBNAIL }
    enum class Colors {MAIN, TITLE, BODY }

    fun getBackdropImages(): Pair<String?, String?>? {
        return if (images.containsKey(Images.BACKDROP.toString())) {
            Pair(images[Images.BACKDROP.toString()], images[Images.BACKDROP_THUMBNAIL.toString()])
        } else {
            getPosterImages()
        }
    }

    fun getPosterImages(): Pair<String?, String?>? =
            Pair(images[Images.POSTER.toString()], images[Images.POSTER_THUMBNAIL.toString()])

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
