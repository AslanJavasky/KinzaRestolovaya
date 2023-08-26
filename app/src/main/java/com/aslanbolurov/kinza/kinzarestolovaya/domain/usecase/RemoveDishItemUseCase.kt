package com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase

import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishItem
import com.aslanbolurov.kinza.kinzarestolovaya.domain.repo.DishRepo
import javax.inject.Inject

class RemoveDishItemUseCase @Inject constructor(
    private val repo: DishRepo
) {
    suspend operator fun invoke(dishItem: DishItem){
        repo.removeDishFromDb(dishItem)
    }
}