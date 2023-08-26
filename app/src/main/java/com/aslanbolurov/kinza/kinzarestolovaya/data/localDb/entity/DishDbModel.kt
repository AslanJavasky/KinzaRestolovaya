package com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "dishes")
data class DishDbModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id:String,
    @ColumnInfo(name = "order_id") val orderId:String?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "cnt") val cnt: Int,
    @ColumnInfo(name = "is_selected") val isSelected:Boolean
)

