package com.aslanbolurov.kinza.kinzarestolovaya.data.dishesDataSource.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DishDto(
    @Json(name = "name")
    val name: String,
    @Json(name = "price")
    val price: String
)