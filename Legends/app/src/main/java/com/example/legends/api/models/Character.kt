package com.example.legends.api.models

import com.squareup.moshi.Json

data class CharacterRes (
    @Json(name = "data")
    val data : Map<String, Character>
)

data class Character(
    @Json(name = "id")
    val id : String,
    @Json(name = "image")
    val image: Image,
    @Json(name = "lore")
    val lore : String,
    @Json(name = "title")
    val title : String
)