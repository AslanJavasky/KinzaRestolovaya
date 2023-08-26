package com.aslanbolurov.kinza.kinzarestolovaya.presentation.dishesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.aslanbolurov.kinza.kinzarestolovaya.databinding.FragmentDishesBinding
import com.aslanbolurov.kinza.kinzarestolovaya.presentation.viewModelDb.ViewModelDb
import com.aslanbolurov.kinza.kinzarestolovaya.presentation.viewModelDb.ViewModelDbFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DishesFragment : Fragment() {

    @Inject
    lateinit var FVM: DishesViewModelFactory
    private val viewModel: DishesViewModel by viewModels { FVM }

    @Inject
    lateinit var FVM_DB: ViewModelDbFactory
    private val viewModelDb: ViewModelDb by viewModels { FVM_DB }




    private var _binding: FragmentDishesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDishesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DishListAdapter(viewModelDb,viewLifecycleOwner.lifecycleScope )
        binding.rvDishes.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.items.collectLatest {
                    adapter.submitList(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}