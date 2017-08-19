package com.cinecor.android.data.source.local

import android.arch.persistence.room.TypeConverter
import com.cinecor.android.data.model.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DbTypeConverters {

    @TypeConverter
    @JvmStatic
    fun stringToMovies(movies: String): List<Movie> =
            Gson().fromJson(movies, object : TypeToken<List<Movie>>() {}.type)

    @TypeConverter
    @JvmStatic
    fun moviesToString(movies: List<Movie>): String =
            Gson().toJson(movies)

    @TypeConverter
    @JvmStatic
    fun listToString(list: List<String>) =
            Gson().toJson(list)

    @TypeConverter
    @JvmStatic
    fun stringToList(list: String) =
            Gson().fromJson<List<String>>(list, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    @JvmStatic
    fun hashmapToString(hashMap: HashMap<String, String>) =
            Gson().toJson(hashMap)

    @TypeConverter
    @JvmStatic
    fun stringTohashmap(hashMap: String) =
            Gson().fromJson<HashMap<String, String>>(hashMap, object : TypeToken<HashMap<String, String>>() {}.type)
}
