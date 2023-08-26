package com.aslanbolurov.kinza.kinzarestolovaya.presentation.cartFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishItem
import com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase.GetAllDishesFromDbUseCase
import com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase.RemoveDishItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val getAllDishesUseCase: GetAllDishesFromDbUseCase,
    private val removeDishUseCase:RemoveDishItemUseCase
) : ViewModel() {


    val items = getAllDishesUseCase()

    fun removeItem(dishItem: DishItem) {
        viewModelScope.launch(Dispatchers.IO) {
            removeDishUseCase(dishItem)
        }
    }
}