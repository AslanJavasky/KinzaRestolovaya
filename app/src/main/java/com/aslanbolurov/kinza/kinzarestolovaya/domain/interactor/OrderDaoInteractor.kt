package com.aslanbolurov.kinza.kinzarestolovaya.domain.interactor

import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.Order
import com.aslanbolurov.kinza.kinzarestolovaya.domain.repo.OrderRepo
import javax.inject.Inject

class OrderDaoInteractor @Inject constructor(
    private val repo: OrderRepo
) {
    suspend fun saveOrderToRoom(order: Order) = repo.saveOrderToRoom(order)
    suspend fun updateOrder(order: Order) = repo.updateOrder(order)
    suspend fun deleteAllOrders() = repo.deleteAllOrders()
}