package com.example.legends.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.legends.api.models.Icon

@Dao
interface CharacterDao {

    @Query("SELECT * FROM Icon")
    suspend fun getAllCharacters() : List<Icon>

    @Query("SELECT EXISTS(SELECT 1 FROM Icon WHERE Icon.id =:id)")
    suspend fun exists(id : String) : Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCharacter(icon : Icon)

    @Query("DELETE FROM Icon WHERE id =:id")
    suspend fun removeCharacter(id: String)
}