package com.mj.kmjhomework.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mj.kmjhomework.R
import com.mj.kmjhomework.data.model.Beer
import com.mj.kmjhomework.data.model.Hops
import com.mj.kmjhomework.data.model.Ingredients
import com.mj.kmjhomework.data.model.Malt
import java.util.concurrent.atomic.AtomicBoolean

@BindingAdapter("setBeers")
fun setBeers(view: RecyclerView, item: List<Beer>?) {
    (view.adapter as? BeerListAdapter)?.run {
        item?.let { updateItems(it) }
        notifyDataSetChanged()
    }
}

@BindingAdapter("onItemClickListener")
fun View.onItemClickListener(clickListener: View.OnClickListener?) {
    clickListener?.also {
        setOnClickListener(OnClickListener(it))
    } ?: setOnClickListener(null)
}

//중복클릭 방지
class OnClickListener(
    private val clickListener: View.OnClickListener,
    private val intervalMs: Long = 1000
) : View.OnClickListener {
    private var canClick = AtomicBoolean(true)

    override fun onClick(v: View?) {
        if (canClick.getAndSet(false)) {
            v?.run {
                postDelayed({
                    canClick.set(true)
                }, intervalMs)
                clickListener.onClick(v)
            }
        }
    }
}

@BindingAdapter("setImage")
fun setImage(view: ImageView, image_url: String?) {
    if (image_url.isNullOrEmpty()) {
        view.visibility = View.INVISIBLE
    } else {
        view.visibility = View.VISIBLE
        Glide.with(view.context).load(image_url).error(R.drawable.ic_drink).into(view)
    }

    /*  Glide.with(view.context).asBitmap().load(thumbnail).error(R.drawable.default_img_3x).override(500, 500).transform(CenterCrop(), RoundedCorners(100))
          .diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view)*/
}

@BindingAdapter("setIngredients")
fun setIngredients(view: RecyclerView, malt: List<Malt>?) {
    (view.adapter as? IngredientsAdapter)?.run {
        malt?.let { updateItems(it) }
        notifyDataSetChanged()
    }
}

@BindingAdapter("setIngredientHops")
fun setIngredientHops(view: RecyclerView, hops: List<Hops>?) {
    (view.adapter as? IngredientHopsAdapter)?.run {
        hops?.let { updateItems(it) }
        notifyDataSetChanged()
    }
}

@BindingAdapter("food")
fun food(view: TextView, foods: List<String>?) {
    if (!foods.isNullOrEmpty()) {
        var tagString = ""
        for (food in foods) {
            tagString += "● ${food}\n"
        }
        view.text = tagString
    }
}

