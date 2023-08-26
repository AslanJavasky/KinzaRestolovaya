package com.aslanbolurov.kinza.kinzarestolovaya.presentation.cartFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aslanbolurov.kinza.kinzarestolovaya.R
import com.aslanbolurov.kinza.kinzarestolovaya.databinding.FragmentCartBinding
import com.aslanbolurov.kinza.kinzarestolovaya.databinding.FragmentDessertsBinding
import com.aslanbolurov.kinza.kinzarestolovaya.presentation.MainActivity
import com.aslanbolurov.kinza.kinzarestolovaya.presentation.dessertsFragment.DessertListAdapter
import com.aslanbolurov.kinza.kinzarestolovaya.presentation.dessertsFragment.DessertsViewModel
import com.aslanbolurov.kinza.kinzarestolovaya.presentation.dessertsFragment.DessertsViewModelFactory
import com.aslanbolurov.kinza.kinzarestolovaya.presentation.viewModelDb.ViewModelDb
import com.aslanbolurov.kinza.kinzarestolovaya.presentation.viewModelDb.ViewModelDbFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {

    @Inject
    lateinit var FVM: CartViewModelFactory
    private val viewModel: CartViewModel by viewModels { FVM }


    @Inject
    lateinit var FVM_DB: ViewModelDbFactory
    private val viewModelDb: ViewModelDb by viewModels { FVM_DB }

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentCartBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter= CartListAdapter(viewModelDb)
        binding.rvDishes.adapter=adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.items.collect {
                adapter.submitList(it)
            }
        }

        binding.btnOrder.setOnClickListener {
            (requireActivity() as MainActivity).hideBottomNavigationBar()
            findNavController().navigate(R.id.action_navigation_cart_to_orderFragment)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}