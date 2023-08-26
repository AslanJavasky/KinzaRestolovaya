package com.aslanbolurov.kinza.kinzarestolovaya.domain.model

import java.util.UUID

data class DishItem(
    val id:String=UUID.randomUUID().toString(),
    val orderId:String?=null,
    val name: String,
    val price: String,
    var type:String,
    var cnt:Int=1,
    var isSelected:Boolean=false
)