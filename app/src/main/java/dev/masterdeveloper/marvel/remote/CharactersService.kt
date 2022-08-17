package dev.masterdeveloper.marvel.remote

import dev.masterdeveloper.marvel.model.Character
import dev.masterdeveloper.marvel.model.ServerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(@Query("offset") offset: Int? = 0, @Query("limit") limit: Int? = 20): ServerResponse<Character>

}