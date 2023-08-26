package com.aslanbolurov.kinza.kinzarestolovaya.di

import android.app.Application
import com.aslanbolurov.kinza.kinzarestolovaya.data.utils.FirebaseAuthUtils
import com.aslanbolurov.kinza.kinzarestolovaya.data.utils.FirebaseUtils
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {


    @Provides
    fun provideFirebaseDb(): FirebaseDatabase {
        return Firebase.database
    }

    @Provides
    fun provideFirebaseCrashlytics(): FirebaseCrashlytics {
        return Firebase.crashlytics
    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    fun provideAuthUI(): AuthUI {
        return AuthUI.getInstance()
    }

    @Provides
    fun provideAuthUtils(
        auth: FirebaseAuth,
        authUI: AuthUI
    ): FirebaseAuthUtils {
        return FirebaseAuthUtils(auth, authUI)
    }

    @Provides
    fun provideFirebaseUtils(
        firebaseDatabase: FirebaseDatabase,
        crashlytics: FirebaseCrashlytics,
        authUtils: FirebaseAuthUtils
    ): FirebaseUtils {
        return FirebaseUtils(firebaseDatabase, crashlytics, authUtils)
    }

}