package com.example.legends.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.legends.api.models.Icon
import com.example.legends.room.database.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM Icon")
    suspend fun getAllCharacters() : Map<String, Icon>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCharacter(icon : Icon)

    @Query("DELETE FROM Icon WHERE id =:id")
    suspend fun removeCharacter(id: String)
}