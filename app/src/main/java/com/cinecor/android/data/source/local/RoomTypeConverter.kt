package com.cinecor.android.data.source.local

import android.arch.persistence.room.TypeConverter
import com.cinecor.android.data.model.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomTypeConverter {

    @TypeConverter
    fun jsonToMovies(movies: String): List<Movie> =
            Gson().fromJson<List<Movie>>(movies, object : TypeToken<List<Movie>>() {}.type)

    @TypeConverter
    fun moviesToJson(movies: List<Movie>): String =
            Gson().toJson(movies)

    @TypeConverter
    fun listToJson(list: List<String>) =
            Gson().toJson(list)

    @TypeConverter
    fun jsonToList(list: String) =
            Gson().fromJson<List<String>>(list, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun hashmapToJson(hashMap: HashMap<String, String>) =
            Gson().toJson(hashMap)

    @TypeConverter
    fun jsonToHashmap(hashMap: String) =
            Gson().fromJson<HashMap<String, String>>(hashMap, object : TypeToken<HashMap<String, String>>() {}.type)
}
