package com.mj.kmjhomework.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mj.kmjhomework.data.model.Malt
import com.mj.kmjhomework.databinding.ItemIngredientsBinding

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>(){
    val items = mutableListOf<Malt>()

    fun updateItems(item: List<Malt>) {
        item.let {
            items.clear()
            items.addAll(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemIngredientsBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(private val binding: ItemIngredientsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Malt) {
            with(binding) {
                malt = item
                executePendingBindings()
            }
        }
    }


}