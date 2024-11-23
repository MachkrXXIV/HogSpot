package com.team.hogspot.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.team.hogspot.model.user.User

class Converters {
    private val gson = Gson()
    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(list: String): List<String> {
        return gson.fromJson(list, Array<String>::class.java).toList()
    }
}