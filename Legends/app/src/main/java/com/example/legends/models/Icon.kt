package com.example.legends.models

import com.squareup.moshi.Json

data class IconsList(
    @Json(name = "data")
    val data : Map<String, Icon>
)

data class Icon(
    @Json(name = "id")
    val id : String,
    @Json(name = "image")
    val image: Image,
    )

data class Image(
    @Json(name = "full")
    val image : String
)