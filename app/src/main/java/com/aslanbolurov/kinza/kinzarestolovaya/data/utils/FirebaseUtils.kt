package com.aslanbolurov.kinza.kinzarestolovaya.data.utils

import android.app.Application
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.Order
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.database.FirebaseDatabase

class FirebaseUtils (
    private val dbFirebase: FirebaseDatabase,
    internal val crashlytics: FirebaseCrashlytics,
    internal val authUtils: FirebaseAuthUtils
) {

    val dbRefence = dbFirebase.getReference(FOLDER_CHILD)


    companion object {

        private const val FOLDER_CHILD = "Orders"
        private var INSTANCE: FirebaseUtils? = null
        private val LOCK = Any()



        fun getInstance(
            application: Application,
            firebaseUtils: FirebaseUtils
        ): FirebaseUtils {
            INSTANCE?.let { firebaseInstance ->
                return firebaseInstance
            }

            synchronized(LOCK) {
                INSTANCE?.let { firebaseInstance ->
                    return firebaseInstance
                }

                INSTANCE = FirebaseUtils(
                    firebaseUtils.dbFirebase,
                    firebaseUtils.crashlytics,
                    firebaseUtils.authUtils
                )
                return FirebaseUtils(
                    firebaseUtils.dbFirebase,
                    firebaseUtils.crashlytics,
                    firebaseUtils.authUtils
                )
            }
        }
    }
}