package com.aslanbolurov.kinza.kinzarestolovaya.data.utils

import com.aslanbolurov.kinza.kinzarestolovaya.R
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthUtils (
    internal val auth: FirebaseAuth,
    internal val authUI: AuthUI
) {


    fun getUserName(): String? {
        val user = auth.currentUser
        return if (user != null) {
            user.displayName
        } else ANONYMOUS
    }

    companion object {
        fun getIntentForSignIN() =
            AuthUI.getInstance().createSignInIntentBuilder()
                .setLogo(R.drawable.logo)
                .setAvailableProviders(
                    listOf(
                        AuthUI.IdpConfig.GoogleBuilder().build(),
                        AuthUI.IdpConfig.EmailBuilder().build(),
                        AuthUI.IdpConfig.PhoneBuilder().build()
                    )
                )
                .build()


        private const val ANONYMOUS = "Anonymous"
    }
}