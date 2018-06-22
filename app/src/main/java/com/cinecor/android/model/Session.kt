package com.cinecor.android.model

data class Session(
        val id: String = "",
        val cinemaId: String = "",
        val movieId: String = "",
        val date: String = "",
        var hours: HashMap<String, List<String>> = HashMap(),
        var movieTitle: String = "",
        var movieImage: String = ""
) {

    enum class Type { NORMAL, VOSE, THREEDIM, JUNIOR }
}
