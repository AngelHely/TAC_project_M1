package com.example.legends.api

import com.squareup.moshi.Json

data class Icon(
    @Json(name = "full")
    val image: String,
    ) {
}