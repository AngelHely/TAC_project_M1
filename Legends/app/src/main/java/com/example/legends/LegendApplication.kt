package com.example.legends

import android.app.Application
import com.example.legends.mvvm.repository.CharacterRepository
import com.example.legends.mvvm.repository.IconRepository
import com.example.legends.mvvm.useCase.CharacterUseCase
import com.example.legends.mvvm.useCase.IconUseCase
import com.example.legends.room.CharacterDao
import com.example.legends.room.CharacterDataBase

class LegendApplication() : Application() {

    private lateinit var  context : CharactersMenuActivity

    fun setContext(context : CharactersMenuActivity) {
        this.context = context
    }

    private val database : CharacterDataBase by lazy {
        CharacterDataBase.getDatabase(this.context)
    }

    private val dao : CharacterDao by lazy {
        database.characterDao()
    }

    // CHARACTER part

    private val characterRepository : CharacterRepository by lazy {
        CharacterRepository(dao)
    }

    val characterUseCase : CharacterUseCase by lazy {
        CharacterUseCase(characterRepository)
    }

    // ICON part

    private val iconRepository : IconRepository by lazy {
        IconRepository(dao)
    }

    val iconUseCase : IconUseCase by lazy {
        IconUseCase(iconRepository)
    }
}