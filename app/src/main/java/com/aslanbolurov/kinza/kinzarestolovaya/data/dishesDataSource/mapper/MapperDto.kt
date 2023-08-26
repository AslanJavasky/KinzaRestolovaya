package com.aslanbolurov.kinza.kinzarestolovaya.data.dishesDataSource.mapper

import com.aslanbolurov.kinza.kinzarestolovaya.data.dishesDataSource.dto.DishDto
import com.aslanbolurov.kinza.kinzarestolovaya.data.dishesDataSource.dto.DishesDto
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishItem
import javax.inject.Inject

class MapperDto @Inject constructor() {

    fun mapDtoListToDishItemList(list: List<DishDto>, type: String) = list.map {
        DishItem(name = it.name, price = it.price, type = type)
    }


}