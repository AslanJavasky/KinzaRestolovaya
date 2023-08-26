package com.aslanbolurov.kinza.kinzarestolovaya.presentation.grillFragment

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class GrillViewModel @Inject constructor(
    private val interactor: DishInteractor,
    private val saveUseCase: SaveDishItemUseCase,
    private val removeUseCase: RemoveDishItemUseCase,
    private val incrementCntOfDishesUceCase: IncrementCntOfDishesUceCase,
    private val decrementCntOfDishesUceCase: DecrementCntOfDishesUceCase,
    private val getAllDishesFromDbUseCase: GetAllDishesFromDbUseCase
) : ViewModel() {


    private val _items = MutableStateFlow<List<DishItem>>(emptyList())
    val items = _items.asStateFlow()

    val dishesFromDb_Flow = getAllDishesFromDbUseCase()
    private lateinit var DB_LIST:List<DishItem>

    init {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching { interactor.getGrills() }
                .fold(
                    onSuccess = { _items.value = it },
                    onFailure = {}
                )
        }
        viewModelScope.launch(Dispatchers.IO) {
            dishesFromDb_Flow.collectLatest {
                DB_LIST=it
            }
        }
    }

    fun saveItem(dishItem: DishItem) {
        viewModelScope.launch(Dispatchers.IO) {
            dishItem.type = DishTypesConst.TYPE_GRILL
            saveUseCase(dishItem)
        }

    }

    fun removeItem(dishItem: DishItem) {
        viewModelScope.launch(Dispatchers.IO) {
            removeUseCase(dishItem)
        }
    }

    fun incrementCntOfDishItem(dishItem: DishItem) {
        viewModelScope.launch(Dispatchers.IO) {
            incrementCntOfDishesUceCase(dishItem)
        }
    }

    fun decrementCntOfDishItem(dishItem: DishItem) {
        viewModelScope.launch(Dispatchers.IO) {
            decrementCntOfDishesUceCase(dishItem)
        }
    }
    fun showToastForAdd() {
        Toast.makeText(App.INSTANCE, R.string.dish_is_added, Toast.LENGTH_LONG).show()
    }

    fun showToastForRemove() {
        Toast.makeText(App.INSTANCE, R.string.dish_is_removed, Toast.LENGTH_LONG).show()
    }
}