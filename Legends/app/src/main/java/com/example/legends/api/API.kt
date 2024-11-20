package com.example.legends.api

import com.example.legends.models.CharacterRes
import com.example.legends.models.IconsList
import com.example.legends.repository.CharacterRepositoryInterface
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


object APIService{
    val retrofitService : CharacterRepositoryInterface by lazy {
        retrofit.create(CharacterRepositoryInterface::class.
        java) }
}
