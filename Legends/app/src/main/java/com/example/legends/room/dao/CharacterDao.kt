package com.example.legends.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.legends.room.database.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM CharacterEntity")
    fun getAllCharacters() : List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCharacter(character : CharacterEntity)

    @Delete
    suspend fun removeCharacter(character: CharacterEntity)
}