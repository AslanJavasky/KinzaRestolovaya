package com.aslanbolurov.kinza.kinzarestolovaya.presentation.dishesFragment

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslanbolurov.kinza.kinzarestolovaya.App
import com.aslanbolurov.kinza.kinzarestolovaya.R
import com.aslanbolurov.kinza.kinzarestolovaya.domain.interactor.DishInteractor
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishItem
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishTypesConst
import com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase.DecrementCntOfDishesUceCase
import com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase.GetAllDishesFromDbUseCase
import com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase.IncrementCntOfDishesUceCase
import com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase.RemoveDishItemUseCase
import com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase.SaveDishItemUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class DishesViewModel @Inject constructor(
    private val interactor: DishInteractor,


) : ViewModel() {

    private val _items = MutableStateFlow<List<DishItem>>(emptyList())
    val items = _items.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching { interactor.getDishes() }
                .fold(
                    onSuccess = {
                        _items.value = it
                        Log.d("aslan555", "$it: ")
                    },
                    onFailure = {
                        Log.d("aslan555", it.message.toString())
                    }
                )
        }
    }

}