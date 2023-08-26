package com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.entity.OrderDbModel

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrderToRoom(order: OrderDbModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateOrder(order: OrderDbModel)

    @Query("DELETE FROM orders")
    suspend fun deleteAllOrders()

}