package com.aslanbolurov.kinza.kinzarestolovaya.presentation.dishesFragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aslanbolurov.kinza.kinzarestolovaya.App
import com.aslanbolurov.kinza.kinzarestolovaya.R
import com.aslanbolurov.kinza.kinzarestolovaya.databinding.DishItemBinding
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishItem
import com.aslanbolurov.kinza.kinzarestolovaya.domain.model.DishTypesConst
import com.aslanbolurov.kinza.kinzarestolovaya.presentation.viewModelDb.ViewModelDb


class DishListAdapter(
    private val viewModelDb: ViewModelDb,
    private val lifecycleScope: LifecycleCoroutineScope,

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

        setIconDependentlyStateSelected(dishItem.isSelected, holder)
        holder.binding.btnToCart.text =
            if (dishItem.isSelected) dishItem.cnt.toString() else ""

        holder.binding.btnToCart.setOnClickListener {
            dishItem.isSelected = !dishItem.isSelected
            if (dishItem.isSelected) {
                viewModelDb.saveItem(dishItem, DishTypesConst.TYPE_DISH)
                viewModelDb.showToastForAdd()
                setIconDependentlyStateSelected(dishItem.isSelected, holder)
//                refreshCntDishes(holder, dishItem)
                holder.binding.btnToCart.text =viewModelDb.getCntOfDish(dishItem)
            } else {

                viewModelDb.removeItem(dishItem)
                viewModelDb.showToastForRemove()
                setIconDependentlyStateSelected(dishItem.isSelected, holder)
            }
        }
        holder.binding.plus.setOnClickListener {
            Log.d("aslan555", "++$viewModelDb ")
            viewModelDb.incrementCntOfDishItem(dishItem)
            holder.binding.btnToCart.text =viewModelDb.getCntOfDish(dishItem)
        }
        holder.binding.minus.setOnClickListener {
            Log.d("aslan555", "--$viewModelDb ")
            viewModelDb.decrementCntOfDishItem(dishItem)
            holder.binding.btnToCart.text =viewModelDb.getCntOfDish(dishItem)
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

    private fun setVisiblePlusMinusBtns(value: Boolean, holder: DishesViewHolder) {
        holder.binding.minus.isVisible = value
        holder.binding.plus.isVisible = value

    }

    private fun refreshCntDishes(
        holder: DishesViewHolder,
        dishItem: DishItem
    ) {

//        viewModelDb.dishesFromDb_Flow.onEach {
//            holder.binding.btnToCart.text =
//                viewModelDb.getCntOfDish(dishItem)
//        }.stateIn(lifecycleScope, SharingStarted.Eagerly, emptyList())

//        lifecycleScope.launch(Dispatchers.IO) {
//            viewModelDb.getCntOfDish_FLOW(dishItem).collectLatest {
//                holder.binding.btnToCart.text =
//                    it
//            }
//        }
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


