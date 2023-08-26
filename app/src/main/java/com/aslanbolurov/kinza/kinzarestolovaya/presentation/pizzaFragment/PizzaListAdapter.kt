package com.aslanbolurov.kinza.kinzarestolovaya.presentation.pizzaFragment

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aslanbolurov.kinza.kinzarestolovaya.App
import com.aslanbolurov.kinza.kinzarestolovaya.R
import com.aslanbolurov.kinza.kinzarestolovaya.databinding.DishItemBinding
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishItem
import com.aslanbolurov.kinza.kinzarestolovaya.presentation.dishesFragment.DishesViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PizzaListAdapter(
    private val viewModel: PizzaViewModel
)
    : ListAdapter<DishItem, DishesViewHolder>(callback) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): DishesViewHolder {

        val binding=
            DishItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DishesViewHolder(binding)

    }

    override fun onBindViewHolder(
        holder: DishesViewHolder, position: Int
    ) {
        val dishItem = getItem(position)
        holder.binding.tvName.text = dishItem.name
        holder.binding.tvPrice.text = dishItem.price
        setIconDependentlyStateSelected(dishItem.isSelected, holder)
        holder.binding.btnToCart.text =
            if (dishItem.isSelected) dishItem.cnt.toString() else ""

        holder.binding.btnToCart.setOnClickListener {
            dishItem.isSelected = !dishItem.isSelected
            if (dishItem.isSelected) {
                viewModel.saveItem(dishItem)
                viewModel.showToastForAdd()
                setIconDependentlyStateSelected(dishItem.isSelected,holder)
                refreshCntDishes(holder, dishItem)
            } else {
                viewModel.removeItem(dishItem)
                viewModel.showToastForRemove()

                setIconDependentlyStateSelected(dishItem.isSelected,holder)
            }
        }
        holder.binding.plus.setOnClickListener {
            Log.d("aslan555", "++$viewModel ")
            viewModel.incrementCntOfDishItem(dishItem)
        }
        holder.binding.minus.setOnClickListener {
            Log.d("aslan555", "--$viewModel ")
            viewModel.decrementCntOfDishItem(dishItem)
        }
    }

    private fun setIconDependentlyStateSelected(
        isSelected: Boolean, holder: DishesViewHolder
    ) {
        val icon = if (isSelected) R.drawable.cart_icon else R.drawable.remove_shopping_cart_24
        holder.binding.btnToCart.icon = ResourcesCompat.getDrawable(
            App.INSTANCE.resources,
            icon,
            null
        )
        setVisiblePlusMinusBtns(isSelected, holder)
        if (!isSelected) holder.binding.btnToCart.text = ""

    }

    private fun setVisiblePlusMinusBtns(value: Boolean,holder: DishesViewHolder) {
        holder.binding.minus.isVisible = value
        holder.binding.plus.isVisible = value

    }

    private fun refreshCntDishes(
        holder: DishesViewHolder,
        dishItem: DishItem
    ) {
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.dishesFromDb_Flow.collectLatest {
                val list = it
                holder.binding.btnToCart.text = if (list.isNotEmpty()) {
                    list.filter { it.id == dishItem.id }.get(0).cnt.toString()
                }else{
                    ""
                }
            }
        }
    }
}

class DishesViewHolder(val binding: DishItemBinding) : RecyclerView.ViewHolder(binding.root)

val callback = object : DiffUtil.ItemCallback<DishItem>() {
    override fun areItemsTheSame(oldItem: DishItem, newItem: DishItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DishItem, newItem: DishItem): Boolean {
        return oldItem == newItem
    }

}


