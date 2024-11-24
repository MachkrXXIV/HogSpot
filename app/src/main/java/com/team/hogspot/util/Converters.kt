package com.team.hogspot.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.team.hogspot.model.geospot.Difficulty
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    @TypeConverter
    fun fromDate(value: String): LocalDateTime {
        return LocalDateTime.parse(value)
    }

    @TypeConverter
    fun toDate(date: LocalDateTime): String {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }

    @TypeConverter
    fun fromDifficulty(value: String): Difficulty {
        return Difficulty.valueOf(value)
    }

    @TypeConverter
    fun toDifficulty(difficulty: Difficulty): String {
        return difficulty.name
    }
}