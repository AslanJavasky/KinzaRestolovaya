package com.aslanbolurov.kinza.kinzarestolovaya.domain.repo

import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishItem
import kotlinx.coroutines.flow.Flow

interface DishRepo {
    //from Datasource
    fun getDishesByType(type:String):List<DishItem>
    //roomDb
    fun getAllDishesFromDb(): Flow<List<DishItem>>
    suspend fun saveDishToDb(dish: DishItem)
    suspend fun incrementCountOfDishesToDb(dish: DishItem)
    suspend fun decrementCountOfDishesToDb(dish: DishItem)
    suspend fun removeDishFromDb(dish: DishItem)
    suspend fun deleteAllDishesFromDb()
    suspend fun getTotalSumOfDishes(): Int

}