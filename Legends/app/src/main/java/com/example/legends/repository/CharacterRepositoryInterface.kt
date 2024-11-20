package com.example.legends.repository

import com.example.legends.models.CharacterRes
import com.example.legends.models.IconsList
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterRepositoryInterface {
    @GET("/cdn/13.16.1/data/fr_FR/champion.json")
    suspend fun getIcons(): IconsList
    @GET("/cdn/13.16.1/data/fr_FR/champion/{ChampID}.json")
    suspend fun getCharacter(@Path("ChampID") champID: String?) : CharacterRes
}