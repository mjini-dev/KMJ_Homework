package com.mj.kmjhomework.network

import com.mj.kmjhomework.data.model.Beer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("beers")
    fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("abv_gt") abv_gt: Float?,
        @Query("abv_lt") abv_lt: Float?,
        @Query("ibu_gt") ibu_gt: Float?,
        @Query("ibu_lt") ibu_lt: Float?,
        @Query("ebc_gt") ebc_gt: Float?,
        @Query("ebc_lt") ebc_lt: Float?,
        @Query("beer_name") beer_name: String?
    ): Call<List<Beer>>

    @GET("beers/{id}")
    fun getSingleBeer(@Path("id") id: Int): Call<List<Beer>>

    @GET("beers/random")
    fun getRandomBeer(): Call<List<Beer>>

}