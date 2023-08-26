package com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase

import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishItem
import com.aslanbolurov.kinza.kinzarestolovaya.domain.repo.DishRepo
import javax.inject.Inject

class GetAllDishesFromDbUseCase @Inject constructor(
    private val repo: DishRepo
) {
    operator fun invoke() =
        repo.getAllDishesFromDb()

}