package com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.typeConverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Moshi
import java.util.Date
import java.util.stream.Collectors

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun convertToString(list: List<String>): String {
        return list.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
    fun convertFromString(str: String): List<String> {
        return str.split(",")
    }

    //implementation("com.google.code.gson:gson:2.9.0")
    @TypeConverter
    fun convertDishesToString(list: List<Map<String, Int>>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun convertDishesFromString(str: String): List<Map<String, Int>> {
        val myType = object : TypeToken<List<Map<String, Int>>>() {}.type
        return Gson().fromJson(str, myType)
    }
}