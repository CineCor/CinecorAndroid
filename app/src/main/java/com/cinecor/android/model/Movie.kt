package com.cinecor.android.model

data class Movie(
        val id: String = "",
        var title: String = "",
        var imagePoster: String = "",
        var imageBackdrop: String? = null,
        var colors: Colors = Colors(),
        var overview: String = "",
        var imdbId: String? = null,
        var director: String? = null,
        var rating: String? = null,
        var duration: Int? = null,
        var trailer: String? = null,
        var releaseDate: String? = null,
        var genres: List<String>? = null,
        var raw: String? = null
) {

    class Colors(
            val main: String = "",
            val titleText: String = "",
            val bodyText: String = ""
    )
}
