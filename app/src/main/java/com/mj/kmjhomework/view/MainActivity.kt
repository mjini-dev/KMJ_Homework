package com.mj.kmjhomework.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.RangeSlider
import com.mj.kmjhomework.R
import com.mj.kmjhomework.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.dialog_search_detail.*
import kotlinx.android.synthetic.main.dialog_search_detail.view.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val viewModel: MainViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            vm = viewModel
            lifecycleOwner = this@MainActivity

            viewModel.loadBeers()

            rvBeerList.let {
                it.adapter = BeerListAdapter(
                    object : BeerListAdapter.ItemClickListener {
                        override fun onItemClick(id: Int) {
                            //btnClear.visibility = View.GONE
                            //디테일 페이지로 이동
                            Log.d(TAG, "onItemClick: $id")
                            val intent = Intent(this@MainActivity, BeerDetailActivity::class.java)
                            intent.putExtra("id", id)
                            startActivity(intent)
                        }
                    }
                )
            }
        }


        //무한스크롤
        rvBeerList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = rvBeerList.layoutManager

                val lastVisibleItem = (layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

                if (viewModel.isNextPage.value == true) {
                    // 마지막으로 보여진 아이템 position 이
                    // 전체 아이템 개수보다 모자란 경우, 데이터를 loadMore 한다
                    if (layoutManager.itemCount <= lastVisibleItem + 8) {
                        viewModel.loadMoreBeers()
                    }
                }
            }
        })

        //이름 검색
        btnSearch.setOnClickListener {
            if (!etSearch.text.toString().isNullOrBlank()) {
                btnClear.visibility = View.VISIBLE
                val name = etSearch.text.toString()
                // val request =
                viewModel.clearBeer()
                viewModel._beeerName.value = name
                viewModel.loadBeers()
            }

            hideSoftInput()
        }

        //
        btnClear.setOnClickListener {
            etSearch.text.clear()
            btnClear.visibility = View.GONE
            viewModel.clearBeer()
            viewModel.loadBeers()
        }

        btnDetailSearch.setOnClickListener {
            showDialog()
        }

        btnDetailClear.setOnClickListener {
            btnDetailClear.visibility = View.GONE
            tvCondition.visibility = View.GONE
            viewModel.clearBeer()
            viewModel.loadBeers()
        }
    }

    private fun hideSoftInput() {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(etSearch.windowToken, 0);
    }

    private fun showDialog() {
        val inflater =getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.dialog_search_detail, null)

        //ABV
        view.rsAbv.addOnSliderTouchListener(object  : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }

            override fun onStopTrackingTouch(slider: RangeSlider) {
            }

        })

        view.rsAbv.addOnChangeListener{ slider, value, fromUser ->
            view.tvAbvRange.text = "${view.rsAbv.values[0].toInt()} - ${view.rsAbv.values[1].toInt()} "
        }

        //IBU
        view.rsIbu.addOnSliderTouchListener(object  : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }

            override fun onStopTrackingTouch(slider: RangeSlider) {
            }

        })

        view.rsIbu.addOnChangeListener{ slider, value, fromUser ->
            view.tvIbuRange.text = "${view.rsIbu.values[0].toInt()} - ${view.rsIbu.values[1].toInt()} "
        }

        //EBC
        view.rsEbc.addOnSliderTouchListener(object  : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }

            override fun onStopTrackingTouch(slider: RangeSlider) {
            }

        })

        view.rsEbc.addOnChangeListener{ slider, value, fromUser ->
            view.tvEbcRange.text = "${view.rsEbc.values[0].toInt()} - ${view.rsEbc.values[1].toInt()} "
        }



        val alertDialog = AlertDialog.Builder(this)
            .setPositiveButton("검색") { dialog, which ->
                Log.d(TAG, "showDialogss: ${view.rsAbv.values[0]}")
                Log.d(TAG, "showDialogss: ${view.rsAbv.values[1]}")

                viewModel.clearBeer()
                viewModel._abvGt.value = view.rsAbv.values[0]
                viewModel._abvLt.value = view.rsAbv.values[1]

                viewModel._ibuGt.value = view.rsIbu.values[0]
                viewModel._ibuLt.value = view.rsIbu.values[1]

                viewModel._ebcGt.value = view.rsEbc.values[0]
                viewModel._ebcLt.value = view.rsEbc.values[1]

                viewModel.loadBeers()

                tvCondition.visibility = View.VISIBLE
                btnDetailClear.visibility = View.VISIBLE
                tvCondition.text = "ABV ${view.rsAbv.values[0].toInt()} - ${view.rsAbv.values[1].toInt()}\nIBU ${view.rsIbu.values[0].toInt()} - ${view.rsIbu.values[1].toInt()}\nEBC ${view.rsEbc.values[0].toInt()} - ${view.rsEbc.values[1].toInt()}"
            }
            .setNeutralButton("취소", null)
            .create()
        //  여백 눌러도 창 안없어지게
        alertDialog.setCancelable(false)

        alertDialog.setView(view)
        alertDialog.show()
    }
}