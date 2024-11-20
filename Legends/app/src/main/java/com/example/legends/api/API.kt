package com.example.legends.api

import com.example.legends.models.CharacterRes
import com.example.legends.models.IconsList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://ddragon.leagueoflegends.com"

val moshiBuilder: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshiBuilder))
    .build()

interface APICharacter {
    @GET("/cdn/13.16.1/data/fr_FR/champion.json")
    suspend fun getIcons(): IconsList
    @GET("/cdn/13.16.1/data/fr_FR/champion/{ChampID}.json")
    suspend fun getCharacter(@Path("ChampID") champID: String?) : CharacterRes
}


object APIService{
    val retrofitService : APICharacter by lazy {
        retrofit.create(APICharacter::class.
        java) }
}
