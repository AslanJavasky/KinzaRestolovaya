package com.aslanbolurov.kinza.kinzarestolovaya

import android.app.Application
import com.aslanbolurov.kinza.kinzarestolovaya.data.localDb.database.DishDatabase
import com.aslanbolurov.kinza.kinzarestolovaya.data.utils.FirebaseUtils
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App:Application( ) {

    lateinit var db: DishDatabase
        private set

    @Inject
    lateinit var firebaseInstance: FirebaseUtils

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        db = DishDatabase.getInstance(this)
    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }

}