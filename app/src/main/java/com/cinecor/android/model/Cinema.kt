package com.cinecor.android.model

data class Cinema(
        val id: String = "",
        var name: String = "",
        var address: String? = null,
        var image: String? = null,
        var rooms: String? = null,
        var phone: String? = null,
        var web: String? = null
)
