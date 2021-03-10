package com.mj.kmjhomework.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mj.kmjhomework.R
import com.mj.kmjhomework.databinding.ActivityBeerDetailBinding
import org.koin.android.ext.android.inject

class BeerDetailActivity : AppCompatActivity() {
    private val TAG = "BeerDetailActivity"
    private val viewModel: MainViewModel by inject()
    var id:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_beer_detail)
        val binding : ActivityBeerDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_beer_detail)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@BeerDetailActivity

            if (intent.hasExtra("id")) {
                id = intent.getIntExtra("id", 1)
            } else Log.e(TAG, "onCreate: 데이터 없음")

            Log.d(TAG, "onCreate: $id")
            viewModel.getSingleBeer(id)
            rvMalt.adapter = IngredientsAdapter()
            rvHops.adapter = IngredientHopsAdapter()

        }


        /*if (intent.hasExtra("id")) {
            id = intent.getIntExtra("id", 1)
        } else Log.e(TAG, "onCreate: 데이터 없음")*/

        /*val binding:ActivityBeerDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_beer_detail)
        binding.apply {
           *//* vm = viewModel
            lifecycleOwner = this@BeerDetailActivity

            Log.d(TAG, "onCreate: $id")
            viewModel.getSingleBeer(id)*//*

        }*/
    }
}