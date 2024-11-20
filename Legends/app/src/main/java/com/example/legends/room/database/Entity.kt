package com.example.legends.room.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name : String
)
