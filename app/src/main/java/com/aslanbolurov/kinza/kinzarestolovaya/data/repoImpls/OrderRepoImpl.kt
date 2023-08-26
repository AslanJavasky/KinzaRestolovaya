package com.aslanbolurov.kinza.kinzarestolovaya.data.repoImpls

import android.annotation.SuppressLint
import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.dao.OrderDao
import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.mapper.OrderMapper
import com.aslanbolurov.kinza.kinzarestolovaya.data.utils.FirebaseUtils
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.Order
import com.aslanbolurov.kinza.kinzarestolovaya.domain.repo.OrderRepo
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class OrderRepoImpl @Inject constructor(
    private val mapper: OrderMapper,
    private val orderDao: OrderDao,
    private val firebaseUtils: FirebaseUtils
):OrderRepo {

    override fun sendOrder(order: Order) {
        val strTimeStamp=SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(Date())
        val ref = "${order.phoneNumber}_$strTimeStamp"
        firebaseUtils.dbRefence.push().setValue(order)
    }

    override suspend fun saveOrderToRoom(order: Order) {
        orderDao.saveOrderToRoom(mapper.mapItemToDbModel(order))
    }

    override suspend fun updateOrder(order: Order) {
        orderDao.updateOrder(mapper.mapItemToDbModel(order))
    }


    override suspend fun deleteAllOrders() {
        orderDao.deleteAllOrders()
    }


}