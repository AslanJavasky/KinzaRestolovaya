package com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase

import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.Order
import com.aslanbolurov.kinza.kinzarestolovaya.domain.repo.OrderRepo
import javax.inject.Inject

class SendOrderToFirebaseUseCase @Inject constructor(
    private val orderRepo: OrderRepo
) {
    operator fun invoke(order: Order){
        orderRepo.sendOrder(order)
    }
}