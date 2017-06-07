package com.cinecor.android.common.model

import com.cinecor.android.utils.DateUtils.formatedHour
import com.cinecor.android.utils.DateUtils.isAfterNow
import java.util.*
import kotlin.collections.HashMap

data class Movie(
        var id: Int? = 0,
        var colors: HashMap<String, String> = HashMap(),
        var images: HashMap<String, String> = HashMap(),
        var hours: List<String> = ArrayList(),
        var genres: List<String> = ArrayList(),
        var rawDescription: String? = null,
        var imdb: String? = null,
        var trailer: String? = null,
        var duration: Int? = 0,
        var releaseDate: String? = null,
        var overview: String? = null,
        var director: String? = null,
        var url: String? = null,
        var rating: Float? = 0.0f,
        var title: String? = null) {

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

    enum class Images {POSTER, POSTER_THUMBNAIL, BACKDROP, BACKDROP_THUMBNAIL }
    enum class Colors {MAIN, TITLE }
}
