package com.cinecor.android.data.model

import com.cinecor.android.utils.DateUtils.formatedHour
import com.cinecor.android.utils.DateUtils.isAfterNow

data class Movie(val id: Int,
                 val title: String,
                 val hours: List<String>,
                 val is3d: Boolean,
                 val isVose: Boolean,
                 val images: HashMap<String, String>,
                 val colors: HashMap<String, String>,
                 val overview: String,
                 val imdb: String?,
                 val rating: Float?,
                 val duration: Int?,
                 val trailer: String?,
                 val releaseDate: String?,
                 val genres: List<String>?,
                 val rawDescription: String?) {

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
