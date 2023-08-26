package com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.dao.DishDao
import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.dao.OrderDao
import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.entity.DishDbModel
import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.entity.OrderDbModel
import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.typeConverter.Converters

@Database(entities = [DishDbModel::class, OrderDbModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class DishDatabase : RoomDatabase() {
    abstract fun dishDao(): DishDao
    abstract fun orderDao(): OrderDao

    companion object {

        @Volatile
        private var INSTANCE: DishDatabase? = null

        fun getInstance(application: Application): DishDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(application).also { INSTANCE = it }
            }

        private fun buildDatabase(application: Application) =
            Room.databaseBuilder(
                application,
                DishDatabase::class.java, "dishes.db"
            )
                .fallbackToDestructiveMigration()
//                .addTypeConverter(TypeConverter)
                .build()
    }
}
