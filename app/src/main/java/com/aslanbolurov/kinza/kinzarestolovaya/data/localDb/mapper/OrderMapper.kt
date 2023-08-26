package com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.mapper

import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.entity.OrderDbModel
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.Order
import javax.inject.Inject

class OrderMapper @Inject constructor(
    private val dishMapper: DishMapper
) {

    fun mapItemToDbModel(order: Order) = OrderDbModel(
        dishes = order.dishes,
        town = order.town,
        street = order.street,
        houseNumber = order.houseNumber,
        flatNumber = order.flatNumber,
        customerName = order.customerName,
        title = order.title,
        phoneNumber = order.phoneNumber,
        totalPrice = order.totalPrice,
        deliveryCost = order.deliveryCost
    )

    fun mapDbModelToItem(orderDbModel: OrderDbModel) = Order(
        id=orderDbModel.id,
        dishes = orderDbModel.dishes,
        town = orderDbModel.town,
        street = orderDbModel.street,
        houseNumber = orderDbModel.houseNumber,
        flatNumber = orderDbModel.flatNumber,
        customerName = orderDbModel.customerName,
        title = orderDbModel.title,
        phoneNumber = orderDbModel.phoneNumber,
        totalPrice = orderDbModel.totalPrice,
        deliveryCost = orderDbModel.deliveryCost
    )

}