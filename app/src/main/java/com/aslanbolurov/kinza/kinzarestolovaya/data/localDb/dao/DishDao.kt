package com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.entity.DishDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDishToDb(dish: DishDbModel)

    @Delete
    suspend fun removeDishFromDb(dish: DishDbModel)

    @Query("DELETE FROM dishes")
    suspend fun deleteAllDishesFromDb()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDishToDb(dish: DishDbModel)

    @Query("SELECT * FROM dishes")
    fun getAllDishes(): Flow<List<DishDbModel>>

    @Query("SELECT SUM(price) FROM dishes")
    suspend fun getTotalSumOfDishes():Int

}




