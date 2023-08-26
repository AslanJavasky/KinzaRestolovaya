package com.aslanbolurov.kinza.kinzarestolovaya.di

import android.app.Application
import com.aslanbolurov.kinza.kinzarestolovaya.App
import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.dao.DishDao
import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.dao.OrderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HiltModule {

    @Provides
    fun provideDishDao(application: Application): DishDao {
        return App.INSTANCE.db.dishDao()
    }

    @Provides
    fun provideOrderDao(application: Application): OrderDao {
        return App.INSTANCE.db.orderDao()
    }

}