package com.mj.kmjhomework.data.model

data class Beer(
    val id: Int,
    val name: String,
    val tagline:String?,
    val first_brewed:String?,
    val description:String?,

    val image_url:String?,

    val abv:Float?,
    val ibu:Float?,
    val target_fg:Float?,
    val target_og:Float?,

    val ebc:Float?,
    val srm:Float?,
    val ph:Float?,
    val attenuation_level:Float?,
    val volume:Volume,
    val boil_volume:Volume,
    val method:Method,

    val ingredients:Ingredients,

    val food_pairing:List<String>?,
    val brewers_tips:String?,
    val contributed_by:String?
)