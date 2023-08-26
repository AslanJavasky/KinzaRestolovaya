package com.aslanbolurov.kinza.kinzarestolovaya.presentation.cartFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aslanbolurov.kinza.kinzarestolovaya.App
import com.aslanbolurov.kinza.kinzarestolovaya.R
import com.aslanbolurov.kinza.kinzarestolovaya.databinding.DishItemBinding
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishItem
import com.aslanbolurov.kinza.kinzarestolovaya.presentation.dishesFragment.DishesViewModel
import com.aslanbolurov.kinza.kinzarestolovaya.presentation.viewModelDb.ViewModelDb
import com.google.android.material.button.MaterialButton

class CartListAdapter(
    private val viewModel: ViewModelDb
) : ListAdapter<DishItem, DishesViewHolder>(callback) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): DishesViewHolder {

        val binding =
            DishItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DishesViewHolder(binding)

    }

    override fun onBindViewHolder(
        holder: DishesViewHolder, position: Int
    ) {
        val dishItem = getItem(position)
        holder.binding.tvName.text = dishItem.name
        holder.binding.tvPrice.text = dishItem.price
        holder.binding.tvCnt.text = App.INSTANCE.getString(R.string.tv_cnt_dishes,dishItem.cnt)
        holder.binding.tvCnt.isVisible=true

        holder.binding.btnToCart.icon=
            ResourcesCompat.getDrawable(
                App.INSTANCE.resources,
                R.drawable.remove_shopping_cart_24,
                null
            )


        holder.binding.btnToCart.setOnClickListener {
            viewModel.removeItem(dishItem)
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