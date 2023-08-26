package com.aslanbolurov.kinza.kinzarestolovaya.domain.model

import javax.inject.Inject

class DishTypesConst @Inject constructor() {
    companion object {
        const val TYPE_DISH = "dish"
        const val TYPE_DESSERT = "dessert"
        const val TYPE_GRILL = "grill"
        const val TYPE_PIZZA = "pizza"
    }
}