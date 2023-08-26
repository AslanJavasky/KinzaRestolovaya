package com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "orders")
data class OrderDbModel(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "dish_ids") val dishes: List<Map<String,Int>>,
    @ColumnInfo(name = "town") val town: String,
    @ColumnInfo(name = "street") val street: String,
    @ColumnInfo(name = "house_number") val houseNumber: String,
    @ColumnInfo(name = "flat_number") val flatNumber: String,
    @ColumnInfo(name = "phone_number") val phoneNumber: String,
    @ColumnInfo(name = "customer_name") val customerName: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "total_price") val totalPrice: String,
    @ColumnInfo(name = "delivery_cost") var deliveryCost:Int,
)