package com.example.legends.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.legends.room.dao.CharacterDao

@Database(entities = [CharacterEntity::class], version = 1)
abstract class CharacterDataBase : RoomDatabase(){
    abstract val characterDao : CharacterDao
}
