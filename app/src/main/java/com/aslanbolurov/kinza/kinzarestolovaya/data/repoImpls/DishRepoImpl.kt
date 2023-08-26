package com.aslanbolurov.kinza.kinzarestolovaya.data.repoImpls

import android.util.Log
import com.aslanbolurov.kinza.kinzarestolovaya.data.utils.DataUtils
import com.aslanbolurov.kinza.kinzarestolovaya.data.dishesDataSource.DataSource
import com.aslanbolurov.kinza.kinzarestolovaya.data.dishesDataSource.mapper.MapperDto
import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.dao.DishDao
import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.mapper.DishMapper
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishItem
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishTypesConst
import com.aslanbolurov.kinza.kinzarestolovaya.domain.repo.DishRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DishRepoImpl @Inject constructor(
    private val mapperDb: DishMapper,
    private val mapperDto: MapperDto,
    private val dataSource: DataSource,
    private val dishDao: DishDao
) : DishRepo {

    override fun getDishesByType(type: String): List<DishItem> {
        when (type) {
            DishTypesConst.TYPE_DISH -> {

                val objWithDaysOfWeek =
                    DataUtils.fromJson(dataSource.getJsonDishes())!!
                if (DataUtils.currentDayOfWeek == 1) {

                    return mapperDto.mapDtoListToDishItemList(
                        objWithDaysOfWeek.Mon.dishes, DishTypesConst.TYPE_DISH
                    )
                } else {
                    return emptyList()
                }
            }

            DishTypesConst.TYPE_DESSERT -> {
                val objWithDaysOfWeek =
                    DataUtils.fromJson(dataSource.getJsonDishes())!!
                if (DataUtils.currentDayOfWeek == 1) {
                    Log.d("aslan555", "${objWithDaysOfWeek.Mon.desserts} ")
                    return mapperDto.mapDtoListToDishItemList(

                        objWithDaysOfWeek.Mon.desserts, DishTypesConst.TYPE_DESSERT
                    )
                } else {
                    return emptyList()
                }
            }

            DishTypesConst.TYPE_GRILL -> {
                val objWithDaysOfWeek =
                    DataUtils.fromJson(dataSource.getJsonDishes())!!
                if (DataUtils.currentDayOfWeek == 1) {
                    return mapperDto.mapDtoListToDishItemList(
                        objWithDaysOfWeek.Mon.grill, DishTypesConst.TYPE_GRILL
                    )
                } else {
                    return emptyList()
                }
            }

            DishTypesConst.TYPE_PIZZA -> {
                val objWithDaysOfWeek =
                    DataUtils.fromJson(dataSource.getJsonDishes())!!
                if (DataUtils.currentDayOfWeek == 1) {
                    return mapperDto.mapDtoListToDishItemList(
                        objWithDaysOfWeek.Mon.pizza, DishTypesConst.TYPE_PIZZA
                    )
                } else {
                    return emptyList()
                }
            }


            else -> {
                return emptyList()
            }
        }
    }

    override fun getAllDishesFromDb(): Flow<List<DishItem>> {
        return dishDao.getAllDishes().map {
            it.map {
                mapperDb.mapDbModelToItem(it)
            }
        }
    }


    override suspend fun saveDishToDb(dish: DishItem) {
        dishDao.saveDishToDb(mapperDb.mapItemToDbModel(dish))
    }

    override suspend fun incrementCountOfDishesToDb(dish: DishItem) {
        dishDao.saveDishToDb(
            mapperDb.mapItemToDbModel(dish.copy(cnt = ++dish.cnt))
        )
    }

    override suspend fun decrementCountOfDishesToDb(dish: DishItem) {
        var cnt=--dish.cnt
        if (cnt<0) cnt=0
        dishDao.saveDishToDb(
            mapperDb.mapItemToDbModel(dish.copy(cnt = cnt))
        )
    }

    override suspend fun removeDishFromDb(dish: DishItem) {
        dishDao.removeDishFromDb(mapperDb.mapItemToDbModel(dish))
    }

    override suspend fun deleteAllDishesFromDb() {
        dishDao.deleteAllDishesFromDb()
    }

    override suspend fun getTotalSumOfDishes(): Int {
        return dishDao.getTotalSumOfDishes()
    }


}