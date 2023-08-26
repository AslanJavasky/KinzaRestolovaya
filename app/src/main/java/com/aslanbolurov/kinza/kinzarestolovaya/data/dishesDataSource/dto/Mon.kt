package com.aslanbolurov.kinza.kinzarestolovaya.data.dishesDataSource.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Mon(
    @Json(name = "desserts")
    val desserts: List<DishDto>,
    @Json(name = "dishes")
    val dishes: List<DishDto>,
    @Json(name = "grill")
    val grill: List<DishDto>,
    @Json(name = "pizza")
    val pizza: List<DishDto>
)