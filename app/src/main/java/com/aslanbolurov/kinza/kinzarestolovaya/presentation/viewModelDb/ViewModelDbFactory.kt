package com.aslanbolurov.kinza.kinzarestolovaya.presentation.viewModelDb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelDbFactory @Inject constructor(
    private val viewModel:ViewModelDb
):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelDb::class.java)){
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown class name:$modelClass")
    }
}