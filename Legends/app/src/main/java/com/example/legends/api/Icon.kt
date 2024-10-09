package com.example.legends.api

import com.squareup.moshi.Json

data class Icon(
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    ) {
}