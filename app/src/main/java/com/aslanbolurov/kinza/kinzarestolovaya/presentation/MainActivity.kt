package com.aslanbolurov.kinza.kinzarestolovaya.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.aslanbolurov.kinza.kinzarestolovaya.App
import com.aslanbolurov.kinza.kinzarestolovaya.R
import com.aslanbolurov.kinza.kinzarestolovaya.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)

        signUpIn()

    }

    fun showBottomNavigationBar(){
        binding.navView.visibility= View.VISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()

    }

    fun hideBottomNavigationBar() {
        binding.navView.visibility= View.GONE
    }


    private fun isDoneAuth(): Boolean =
        App.INSTANCE.firebaseInstance.authUtils.auth.currentUser != null


    private fun signUpIn() {
        Log.d("aslan555", "uid:${App.INSTANCE.firebaseInstance.authUtils.auth.currentUser} ")
        if (!isDoneAuth()) {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}