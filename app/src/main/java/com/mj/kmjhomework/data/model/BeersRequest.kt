package com.mj.kmjhomework.data.model

data class BeersRequest (
    val page: Int,
    val per_page: Int,
    val abv_gt: Float?,
    val abv_lt: Float?,
    val ibu_gt: Float?,
    val ibu_lt: Float?,
    val ebc_gt: Float?,
    val ebc_lt: Float?,
    val beer_name: String?
)