package com.example.data.dataSource.remote

import com.example.data.dataSource.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("/characters")
    suspend fun getAllCharacters(): Response<List<Character>>

    @GET("/characters/{id}")
    suspend fun getCharacterDetail(@Path("id") id: Int): Response<Character>
}