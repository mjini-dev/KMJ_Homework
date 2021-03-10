package com.mj.kmjhomework.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mj.kmjhomework.data.model.Beer
import com.mj.kmjhomework.databinding.ItemBeerListBinding

class BeerListAdapter(private val listener : ItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val items = mutableListOf<Beer>()

    fun updateItems(item: List<Beer>) {
        item.let {
            items.clear()
            items.addAll(it)
        }
    }

    interface ItemClickListener {
        fun onItemClick(id:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =  ItemBeerListBinding.inflate(inflater,parent,false)
        return ViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(items[position])
    }

    class ViewHolder(private val binding: ItemBeerListBinding, listener: ItemClickListener) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.clickListener = listener
        }

        fun bind(item: Beer) {
            with(binding) {
                position = adapterPosition
                beer = item
                executePendingBindings()
            }
        }
    }
}