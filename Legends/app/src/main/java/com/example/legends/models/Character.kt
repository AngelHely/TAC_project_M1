package com.example.legends.models

import com.squareup.moshi.Json


data class Character(
    @Json(name = "id")
    val id : String,
    @Json(name = "image")
    val image: Icon,
    @Json(name = "lore")
    val lore : String
)