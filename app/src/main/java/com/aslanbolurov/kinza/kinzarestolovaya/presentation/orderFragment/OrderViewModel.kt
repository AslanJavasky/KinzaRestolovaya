package com.aslanbolurov.kinza.kinzarestolovaya.presentation.orderFragment

import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslanbolurov.kinza.kinzarestolovaya.App
import com.aslanbolurov.kinza.kinzarestolovaya.data.utils.DataUtils
import com.aslanbolurov.kinza.kinzarestolovaya.domain.interactor.OrderDaoInteractor
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.Order
import com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase.GetTotalSumOfDishesByOrderIdUseCase
import com.aslanbolurov.kinza.kinzarestolovaya.domain.usecase.SendOrderToFirebaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class OrderViewModel @Inject constructor(
    private val getTotalSumUseCase: GetTotalSumOfDishesByOrderIdUseCase,
    private val orderDaoInteractor: OrderDaoInteractor,
    private val sendOrderToFirebaseUseCase: SendOrderToFirebaseUseCase
) : ViewModel() {

    private val _cart_sum = MutableStateFlow(0)
    val cart_sum = _cart_sum.asStateFlow()

    private val _order = MutableStateFlow(
        Order(
            UUID.randomUUID().toString(),
            emptyList(),
            "",
            "",
            "",
            "",
            "",
            "",
            "Любимой доченке, приятного аппетита))",
            "",
            0,

        )
    )
    val order = _order.asStateFlow()

    fun refreshTotalSum() {
        viewModelScope.launch(Dispatchers.IO) {
            _cart_sum.value = getTotalSumUseCase()
        }
    }


    fun getAdapterForTownSpinner() =
        ArrayAdapter(
            App.INSTANCE,
            android.R.layout.simple_spinner_dropdown_item,
            DataUtils.getAllTowns()
        )

    fun calculateDeliveryCost(selectedTown: String): Int {
        _order.value.deliveryCost = DataUtils.getDeliveryCost(selectedTown, _cart_sum.value)
        return _order.value.deliveryCost
    }


    fun setName(name: String) {
        _order.value.customerName = name
    }

    fun setPhoneNumber(number: String) {
        _order.value.phoneNumber = number
    }

    fun setTitle(title: String) {
        _order.value.title = title
    }

    fun setTown(town: String) {
        _order.value.town = town
    }

    fun setStreet(street: String) {
        _order.value.street = street
    }

    fun setHouseNum(house: String) {
        _order.value.houseNumber = house
    }

    fun setFlatNum(flat: String) {
        _order.value.flatNumber = flat
    }

    fun setDeliveryCost(cost: Int) {
        _order.value.deliveryCost = cost
    }

    fun sendOrderToFirebaseDb() {
        _order.value.totalPrice = (_cart_sum.value + _order.value.deliveryCost).toString()
        sendOrderToFirebaseUseCase(_order.value)
    }

}



