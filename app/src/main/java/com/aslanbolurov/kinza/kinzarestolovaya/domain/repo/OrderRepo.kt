package com.aslanbolurov.kinza.kinzarestolovaya.domain.repo

import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.Order

interface OrderRepo {

    fun sendOrder(order: Order)

    suspend fun saveOrderToRoom(order: Order)
    suspend fun updateOrder(order: Order)
    suspend fun deleteAllOrders()
}