package com.mj.kmjhomework.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mj.kmjhomework.data.model.Beer
import com.mj.kmjhomework.data.model.BeersRequest
import com.mj.kmjhomework.data.source.repository.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val TAG = "MainViewModel"

    private var page = 0
    private val per_page = 25
    private val _isNextPage = MutableLiveData<Boolean>().apply { value = false }
    val isNextPage: LiveData<Boolean> = _isNextPage

    val _beers = MutableLiveData<List<Beer>>()
    val beers: LiveData<List<Beer>> = _beers

    private val _beerDetails = MutableLiveData<Beer>()
    val beerDetails: LiveData<Beer> = _beerDetails

    var _abvGt = MutableLiveData<Float>().apply { value = null }
    val abvGt: LiveData<Float> = _abvGt
    var _abvLt = MutableLiveData<Float>().apply { value = null }
    val abvLt: LiveData<Float> = _abvLt

    var _ibuGt = MutableLiveData<Float>().apply { value = null }
    val ibuGt: LiveData<Float> = _ibuGt
    var _ibuLt = MutableLiveData<Float>().apply { value = null }
    val ibuLt: LiveData<Float> = _ibuLt

    var _ebcGt = MutableLiveData<Float>().apply { value = null }
    val ebcGt: LiveData<Float> = _ebcGt
    var _ebcLt = MutableLiveData<Float>().apply { value = null }
    val ebcLt: LiveData<Float> = _ebcLt

    var _beeerName = MutableLiveData<String>().apply { value = null }
    val beeerName: LiveData<String> = _beeerName

    private fun getBeerRequest(): BeersRequest {
        page++
        return BeersRequest(page, per_page, abvGt.value, abvLt.value, ibuGt.value, ibuLt.value, ebcGt.value, ebcLt.value, beeerName.value)
    }

    fun loadBeers() {
        repository.getBeers(
            getBeerRequest(),
            onSuccess = { response ->
                if (response.isNullOrEmpty()) {
                    if (beers.value.isNullOrEmpty()) {
                        Log.d(TAG, "loadBeers: beers.value.isNullOrEmpty")
                    }
                } else {
                    _beers.value = beers.value?.plus(response) ?: response
                    _isNextPage.value = true
                }
            },
            notSuccessStatus = { notSuccessStatus ->
                Log.d(TAG, "loadBeers notSuccessStatus: $notSuccessStatus")

            },
            onFailure = { failureError ->
                Log.d(TAG, "loadBeers failureError: $failureError")
            }
        )
    }

    fun loadRandomBeer() {
        repository.getRandomBeer(
            onSuccess = { response ->
                Log.d(TAG, "loadRandomBeer onSuccess: $response")
            },
            notSuccessStatus = { notSuccessStatus ->
                Log.d(TAG, "loadRandomBeer notSuccessStatus: $notSuccessStatus")

            },
            onFailure = { failureError ->
                Log.d(TAG, "loadRandomBeer failureError: $failureError")
            }
        )
    }

    fun loadMoreBeers() {
        _isNextPage.value = false
        loadBeers()
    }


    fun getSingleBeer(id: Int) {
        repository.getSingleBeer(id,
            onSuccess = { response ->
                _beerDetails.value = response[0]
                Log.d(TAG, "getSingleBeer: ${response[0]}")

            },
            notSuccessStatus = { notSuccessStatus ->
                Log.d(TAG, "getSingleBeer notSuccessStatus: $notSuccessStatus")

            },
            onFailure = { failureError ->
                Log.d(TAG, "getSingleBeer failureError: $failureError")
            })
    }

    fun clearBeer() {
        page = 0
        _beeerName.value = null
        _abvGt.value = null
        _abvLt.value = null
        _ibuGt.value = null
        _ibuLt.value = null
        _ebcGt.value = null
        _ebcLt.value = null
        _beers.value = null
    }


}