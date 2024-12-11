package com.example.legends.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.legends.api.models.Icon

@Database(entities = [Icon::class], version = 3)
abstract class CharacterDataBase : RoomDatabase(){
    abstract fun characterDao() : CharacterDao


    companion object {
        @Volatile
        private var instance: CharacterDataBase? = null
        fun getDatabase(context: Context): CharacterDataBase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDataBase::class.java,
                    "Legend"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}
