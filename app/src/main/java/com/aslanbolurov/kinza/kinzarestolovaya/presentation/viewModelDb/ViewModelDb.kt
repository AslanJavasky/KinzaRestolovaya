package com.aslanbolurov.kinza.kinzarestolovaya.presentation.viewModelDb

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslanbolurov.kinza.kinzarestolovaya.App
import com.aslanbolurov.kinza.kinzarestolovaya.R
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishItem
import com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase.DecrementCntOfDishesUceCase
import com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase.GetAllDishesFromDbUseCase
import com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase.IncrementCntOfDishesUceCase
import com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase.RemoveDishItemUseCase
import com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase.SaveDishItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelDb @Inject constructor(
    private val saveUseCase: SaveDishItemUseCase,
    private val removeUseCase: RemoveDishItemUseCase,
    private val incrementCntOfDishesUceCase: IncrementCntOfDishesUceCase,
    private val decrementCntOfDishesUceCase: DecrementCntOfDishesUceCase,
    private val getAllDishesFromDbUseCase: GetAllDishesFromDbUseCase
) : ViewModel() {

    var dishesFromDb_Flow: Flow<List<DishItem>> = getAllDishesFromDbUseCase()
    private lateinit var DB_LIST: List<DishItem>

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dishesFromDb_Flow.collectLatest {
                DB_LIST = it
            }
        }
    }


    fun getCntOfDish(dishItem: DishItem): String {
        return if (DB_LIST.isNotEmpty()) {
            DB_LIST.filter { it.id == dishItem.id }.first().cnt.toString()
        } else {
            ""
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

    fun saveItem(dishItem: DishItem, type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dishItem.type = type
            saveUseCase(dishItem)
        }

    }

    fun removeItem(dishItem: DishItem) {
        viewModelScope.launch(Dispatchers.IO) {
            removeUseCase(dishItem)
        }
    }

    fun showToastForAdd() {
        Toast.makeText(App.INSTANCE, R.string.dish_is_added, Toast.LENGTH_LONG).show()
    }

    fun showToastForRemove() {
        Toast.makeText(App.INSTANCE, R.string.dish_is_removed, Toast.LENGTH_LONG).show()
    }

}