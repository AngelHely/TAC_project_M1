package com.example.legends.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = ""

val moshiBuilder: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshiBuilder))
    .build()

interface CharacterIconAPI {
    @GET("icons")
    suspend fun getIcons(): List<Icon>
}


object APIService{
    val retrofitIconService : CharacterIconAPI by lazy {
        retrofit.create(CharacterIconAPI::class.
        java) }
}
