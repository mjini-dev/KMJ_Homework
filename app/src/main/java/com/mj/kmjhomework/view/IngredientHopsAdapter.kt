package com.mj.kmjhomework.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mj.kmjhomework.data.model.Hops
import com.mj.kmjhomework.data.model.Malt
import com.mj.kmjhomework.databinding.ItemIngredientHopsBinding

class IngredientHopsAdapter : RecyclerView.Adapter<IngredientHopsAdapter.ViewHolder>(){
    val items = mutableListOf<Hops>()

    fun updateItems(item: List<Hops>) {
        item.let {
            items.clear()
            items.addAll(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemIngredientHopsBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(private val binding: ItemIngredientHopsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Hops) {
            with(binding) {
                hops = item
                executePendingBindings()
            }
        }
    }


}