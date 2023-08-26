package com.aslanbolurov.kinza.kinzarestolovaya.di

import com.aslanbolurov.kinza.kinzarestolovaya.data.repoImpls.DishRepoImpl
import com.aslanbolurov.kinza.kinzarestolovaya.data.repoImpls.OrderRepoImpl
import com.aslanbolurov.kinza.kinzarestolovaya.domain.repo.DishRepo
import com.aslanbolurov.kinza.kinzarestolovaya.domain.repo.OrderRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BindImpls {

    @Binds
    fun bindsDishRepo(
        dishRepoImpl: DishRepoImpl
    ):DishRepo

    @Binds
    fun bindsOrderRepo(
        orderRepoImpl: OrderRepoImpl
    ):OrderRepo

}