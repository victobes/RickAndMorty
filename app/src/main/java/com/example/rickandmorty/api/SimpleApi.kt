package com.example.rickandmorty.api

import com.example.rickandmorty.api.model.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {

    @GET("api/character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterList>

    @GET("api/character")
    suspend fun getCharactersByStatusAndGender(
        @Query("status") status: String,
        @Query("gender") gender: String,
        @Query("page") page: Int
    ): Response<CharacterList>

    @GET("api/character")
    suspend fun getCharactersByStatus(
        @Query("status") status: String,
        @Query("page") page: Int
    ): Response<CharacterList>

    @GET("api/character")
    suspend fun getCharactersByGender(
        @Query("gender") gender: String,
        @Query("page") page: Int
    ): Response<CharacterList>

    @GET("api/character")
    suspend fun getCharactersByName(
        @Query("name") name: String,
    ): Response<CharacterList>
}