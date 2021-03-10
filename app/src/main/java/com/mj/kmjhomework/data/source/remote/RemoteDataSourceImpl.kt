package com.mj.kmjhomework.data.source.remote

import com.mj.kmjhomework.data.model.Beer
import com.mj.kmjhomework.data.model.BeersRequest
import com.mj.kmjhomework.network.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class RemoteDataSourceImpl(private val retrofitApi: RetrofitInterface) : RemoteDataSource {
    override fun getBeers(
        request: BeersRequest,
        onSuccess: (response: List<Beer>) -> Unit,
        notSuccessStatus: (errorCode: Int) -> Unit,
        onFailure: (e: Throwable) -> Unit
    ) {
        retrofitApi.getBeers(
            request.page,
            request.per_page,
            request.abv_gt,
            request.abv_lt,
            request.ibu_gt,
            request.ibu_lt,
            request.ebc_gt,
            request.ebc_lt,
            request.beer_name
        )
            .enqueue(object : Callback<List<Beer>> {
                override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
                    val responseBody = response.body()
                    if (responseBody != null && response.isSuccessful) {
                        onSuccess(responseBody)
                    } else {
                        val errorCode = HttpException(response).code()
                        if (errorCode in 400..599) {
                            notSuccessStatus(errorCode)
                        }
                        onFailure(HttpException(response))
                    }
                }

            })
    }

    override fun getSingleBeer(id: Int, onSuccess: (response: List<Beer>) -> Unit, notSuccessStatus: (errorCode: Int) -> Unit, onFailure: (e: Throwable) -> Unit) {
        retrofitApi.getSingleBeer(id).enqueue(object : Callback<List<Beer>> {
            override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
                val responseBody = response.body()
                if (responseBody != null && response.isSuccessful) {
                    onSuccess(responseBody)
                } else {
                    val errorCode = HttpException(response).code()
                    if (errorCode in 400..599) {
                        notSuccessStatus(errorCode)
                    }
                    onFailure(HttpException(response))
                }
            }

        })
    }

    override fun getRandomBeer(
        onSuccess: (response: List<Beer>) -> Unit,
        notSuccessStatus: (errorCode: Int) -> Unit,
        onFailure: (e: Throwable) -> Unit
    ) {
        retrofitApi.getRandomBeer().enqueue(object : Callback<List<Beer>> {
            override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
                val responseBody = response.body()
                if (responseBody != null && response.isSuccessful) {
                    onSuccess(responseBody)
                } else {
                    val errorCode = HttpException(response).code()
                    if (errorCode in 400..599) {
                        notSuccessStatus(errorCode)
                    }
                    onFailure(HttpException(response))
                }
            }

        })
    }
}