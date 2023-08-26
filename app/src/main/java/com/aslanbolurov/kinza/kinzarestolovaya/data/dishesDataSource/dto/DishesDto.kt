package com.aslanbolurov.kinza.kinzarestolovaya.data.dishesDataSource.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DishesDto(
    @Json(name = "Mon")
    val Mon: Mon
)