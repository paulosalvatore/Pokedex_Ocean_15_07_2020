package com.oceanbrasil.pokedex_ocean_15_07_2020.api

import com.oceanbrasil.pokedex_ocean_15_07_2020.model.PokemonInfoResult
import com.oceanbrasil.pokedex_ocean_15_07_2020.model.PokemonsResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("pokemon")
    fun listarPokemons(
        @Query("offset") offset: Long,
        @Query("limit") limit: Long?
    ): Call<PokemonsResult>

    @GET("pokemon/{id}")
    fun visualizarPokemon(
        @Path("id") id: Long
    ): Call<PokemonInfoResult>
}
