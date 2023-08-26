package com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.mapper

import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.entity.DishDbModel
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishItem
import javax.inject.Inject

class DishMapper @Inject constructor() {

    fun mapItemToDbModel(dish: DishItem)=DishDbModel(
        id=dish.id,
        orderId =dish.orderId ,
        name = dish.name,
        price = dish.price,
        type = dish.type,
        cnt = dish.cnt,
        isSelected = dish.isSelected
    )

    fun mapDbModelToItem(dishDbModel: DishDbModel)= DishItem(
        id=dishDbModel.id,
        orderId =dishDbModel.orderId ,
        name = dishDbModel.name,
        price = dishDbModel.price,
        type = dishDbModel.type,
        cnt = dishDbModel.cnt,
        isSelected = dishDbModel.isSelected
    )
}