package com.aslanbolurov.kinza.kinzarestolovaya.presentation.splashFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.aslanbolurov.kinza.kinzarestolovaya.R
import com.aslanbolurov.kinza.kinzarestolovaya.databinding.FragmentSplashBinding
import com.aslanbolurov.kinza.kinzarestolovaya.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStart.setOnClickListener {
            (requireActivity() as MainActivity).showBottomNavigationBar()
            findNavController().navigate(R.id.action_splashFragment_to_navigation_dishes)
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).hideBottomNavigationBar()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}