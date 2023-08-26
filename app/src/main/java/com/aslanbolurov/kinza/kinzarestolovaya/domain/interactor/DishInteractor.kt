package com.aslanbolurov.kinza.kinzarestolovaya.domain.interactor

import android.util.Log
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishItem
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishTypesConst
import com.aslanbolurov.kinza.kinzarestolovaya.domain.repo.DishRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DishInteractor @Inject constructor(
    private val repo:DishRepo
){
    fun getDishes(): List<DishItem> {
        val log= repo.getDishesByType(DishTypesConst.TYPE_DISH)
        Log.d("aslan555", "$log")
        return log
    }
    fun getDesserts()=repo.getDishesByType(DishTypesConst.TYPE_DESSERT)
    fun getGrills()=repo.getDishesByType(DishTypesConst.TYPE_GRILL)
    fun getPizza()=repo.getDishesByType(DishTypesConst.TYPE_PIZZA)
}