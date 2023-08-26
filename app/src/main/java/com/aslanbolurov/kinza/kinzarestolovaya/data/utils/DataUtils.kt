package com.aslanbolurov.kinza.kinzarestolovaya.data.utils

import android.util.Log
import com.aslanbolurov.kinza.kinzarestolovaya.data.dishesDataSource.dto.DishesDto
import com.squareup.moshi.Moshi

class DataUtils {
    companion object {
        fun fromJson(json: String) =
            Moshi.Builder().build().adapter(DishesDto::class.java)
                .fromJson(json)


        val currentDayOfWeek
            get() = 1


        private val deliveryPriceList = listOf<OrderPrice>(
            OrderPrice(
                "Пригородное", 999, 1999, 300,
                2000, null, 200, null, null, null
            ),
            OrderPrice(
                "Псауче-Дахе", 999, 1999, 450,
                2000, 2999, 350, 3000, null, 250
            ),
            OrderPrice(
                "Садовое", 999, 1999, 350,
                2000, null, 250, null, null, null
            ),
            OrderPrice(
                "Светлое", 999, 1999, 450,
                2000, null, 350, null, null, null
            ),
            OrderPrice(
                "Счастливое", 999, 1999, 450,
                2000, null, 350, null, null, null
            ),
            OrderPrice(
                "Таллыкъ", 999, 1999, 550,
                2000, null, 450, null, null, null
            ),
            OrderPrice(
                "Тепличный комбинат", 999, 1999, 450,
                2000, null, 350, null, null, null
            ),
            OrderPrice(
                "Усть-Джегута", 999, 1999, 400,
                2000, 2999, 300, 3000, null, 200
            ),
            OrderPrice(
                "Хабез", 999, 1999, 650,
                2000, 2999, 550, 3000, null, 450
            ),
            OrderPrice(
                "Холоднородниковское", 999, 1999, 450,
                2000, null, 350, null, null, null
            ),
            OrderPrice(
                "Чапаевское", 999, 1999, 300,
                2000, null, 200, null, null, null
            ),
            OrderPrice(
                "Эльбурган", 999, 1999, 550,
                2000, null, 450, null, null, null
            ),
            OrderPrice(
                "Эркен-Халк", 999, 1999, 500,
                2000, 2999, 400, 3000, null, 300
            ),
            OrderPrice(
                "Эркен-шахар", 999, 1999, 500,
                2000, 2999, 400, 3000, null, 300
            ),
            OrderPrice(
                "Эркен-юрт", 999, 1999, 700,
                2000, 2999, 600, 3000, null, 400
            ),
            OrderPrice(
                "Дружба", 300, null, 100,
                null, null, null, null, null, null
            ),
            OrderPrice(
                "Псыж", 300, null, 100,
                null, null, 200, null, null, null
            ),
            OrderPrice(
                "Юбилейное", 300, null, 100,
                2000, null, 200, null, null, null
            ),
            OrderPrice(
                "Хутор Чапаевский", 999, 1999, 300,
                2000, null, 200, null, null, null
            ),
            OrderPrice(
                "Черкесск", 150, null, 100,
                2000, null, 200, null, null, null
            ),
            OrderPrice(
                "Адыге-Хабль", 999, 1999, 400,
                2000, null, 300, null, null, null
            ),
            OrderPrice(
                "Адиль-халк", 999, 1999, 450,
                2000, null, 350, null, null, null
            ),
            OrderPrice(
                "Бавуко", 999, 1999, 450,
                2000, 2999, 350, 3000, null, 250
            ),
            OrderPrice(
                "Зеюко", 999, 1999, 550,
                2000, 2999, 450, 3000, null, 350
            ),
            OrderPrice(
                "Знаменка", 999, 1999, 350,
                2000, 2999, 250, 3000, null, 150
            ),
            OrderPrice(
                "Икон-халк", 999, 1999, 550,
                2000, 2999, 450, 3000, null, 350
            ),
            OrderPrice(
                "Ильичевское", 999, 1999, 350,
                2000, 2999, 250, 3000, null, 150
            ),
            OrderPrice(
                "Кавказский", 999, 1999, 400,
                2000, null, 300, null, null, null
            ),
            OrderPrice(
                "Кош-Хабль", 999, 1999, 550,
                2000, 2999, 450, 3000, null, 300
            ),
            OrderPrice(
                "Койдан", 999, 1999, 350,
                2000, null, 300, null, null, null
            ),
            OrderPrice(
                "Малый Зеленчук", 999, 1999, 400,
                2000, null, 300, null, null, null
            ),
            OrderPrice(
                "Московский", 999, 1999, 450,
                2000, null, 350, null, null, null
            ),
            OrderPrice(
                "Николаевское", 999, 1999, 450,
                2000, 2999, 350, 3000, null, 250
            ),
            OrderPrice(
                "Новохумаринский", 999, 1999, 450,
                2000, null, 350, null, null, null
            ),
        )

        fun getAllTowns() = deliveryPriceList.map { it.town }.toList().sorted()
        fun getDeliveryCost(town: String, total: Int): Int {
            val townItem = deliveryPriceList.filter { it.town == town }[0]
            with(townItem) {
                lowerPriceBound_3?.let {
                    if (total >= it) return costDelivery_3!!
                }
                lowerPriceBound_2?.let {
                    if (total >= it) return costDelivery_2!!
                }
                if (total >= lowerPriceBound_1) return costDelivery_1

            }
            return 0
        }

    }
}

data class OrderPrice(
    val town: String,
    val lowerPriceBound_1: Int,
    val upperPriceBound_1: Int?,
    val costDelivery_1: Int,
    val lowerPriceBound_2: Int?,
    val upperPriceBound_2: Int?,
    val costDelivery_2: Int?,
    val lowerPriceBound_3: Int?,
    val upperPriceBound_3: Int?,
    val costDelivery_3: Int?
)

