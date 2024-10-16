package com.example.legends.api

import com.squareup.moshi.Json

data class ChampionsList(
    @Json(name = "data")
    val data : Map<String, Champion>
)

data class Champion(
    @Json(name = "id")
    val id : String,
    @Json(name = "image")
    val image: Icon
)