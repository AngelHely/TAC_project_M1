package com.example.legends.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://ddragon.leagueoflegends.com/"

val moshiBuilder: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshiBuilder))
    .build()

interface CharacterIconAPI {
    @GET("/cdn/13.16.1/data/fr_FR/champion.json")
    suspend fun getIcons(): ChampionsList
}


object APIService{
    val retrofitIconService : CharacterIconAPI by lazy {
        retrofit.create(CharacterIconAPI::class.
        java) }
}
