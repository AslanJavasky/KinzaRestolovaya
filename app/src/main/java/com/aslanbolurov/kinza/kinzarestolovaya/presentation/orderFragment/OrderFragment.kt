package com.aslanbolurov.kinza.kinzarestolovaya.presentation.orderFragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.aslanbolurov.kinza.kinzarestolovaya.App
import com.aslanbolurov.kinza.kinzarestolovaya.R
import com.aslanbolurov.kinza.kinzarestolovaya.databinding.FragmentOrderBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@AndroidEntryPoint
class OrderFragment : Fragment(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var FVM: OrderViewModelFactory

    private val viewModel: OrderViewModel by viewModels { FVM }

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        viewModel.refreshTotalSum()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerAdapter()
        setCherkessByDefaultToSpinner()
        binding.spinnerTowns.onItemSelectedListener = this

        binding.editName.setText(
            App.INSTANCE.firebaseInstance.authUtils.getUserName().toString()
        )

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cart_sum.collectLatest {
                    binding.tvPrice.text = App.INSTANCE
                        .getString(R.string.tv_total_price, "$it")
                }
            }
        }

        binding.btnOrder.setOnClickListener {
            if (binding.editName.text.toString().isNotBlank()
                && binding.editPhoneNumber.text.toString().isNotBlank()
                && binding.editOrderTitle.text.toString().isNotBlank()
                && binding.editStreet.text.toString().isNotBlank()
                && binding.editHousenum.text.toString().isNotBlank()
            ) {

                with(viewModel) {
                    setName(binding.editName.text.toString())
                    setPhoneNumber(binding.editPhoneNumber.text.toString())
                    setTitle(binding.editOrderTitle.text.toString())
                    setStreet(binding.editStreet.text.toString())
                    setHouseNum(binding.editHousenum.text.toString())
                    setFlatNum(binding.editFlatnum.text.toString())

                    sendOrderToFirebaseDb()
                }

            } else {
                Toast.makeText(App.INSTANCE, "Заполните все поля", Toast.LENGTH_LONG).show()
            }
        }
    }



    private fun setRecyclerAdapter() {
        val adapter = viewModel.getAdapterForTownSpinner()
        binding.spinnerTowns.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshTotalSum()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val v: View = binding.spinnerTowns.getSelectedView()
        (v as TextView).setTextColor(Color.WHITE)
        val selectedTown = binding.spinnerTowns.selectedItem as String
        viewModel.setTown(selectedTown)
        binding.tvDeliveryCost.text = App.INSTANCE.getString(
            R.string.tv_delivery_cost,
            viewModel.calculateDeliveryCost(selectedTown).toString()
        )
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        setCherkessByDefaultToSpinner()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setCherkessByDefaultToSpinner() {
        binding.spinnerTowns.setSelection(28, true)
        val v: View = binding.spinnerTowns.getSelectedView()
        (v as TextView).setTextColor(Color.WHITE)
        val selectedTown = binding.spinnerTowns.selectedItem as String
        viewModel.setTown(selectedTown)
        binding.tvDeliveryCost.text = App.INSTANCE.getString(
            R.string.tv_delivery_cost,
            viewModel.calculateDeliveryCost(selectedTown).toString()
        )

    }

}