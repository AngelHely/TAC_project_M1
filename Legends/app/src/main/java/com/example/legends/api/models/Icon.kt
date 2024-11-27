package com.example.legends.api.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class IconsList(
    @Json(name = "data")
    val data : Map<String, Icon>
)

@Entity
data class Icon(
    @Json(name = "id")
    @PrimaryKey(autoGenerate = false)
    val id : String,
    )

data class Image(
    @Json(name = "full")
    val image : String
)