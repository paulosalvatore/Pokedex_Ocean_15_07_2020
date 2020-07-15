package com.oceanbrasil.pokedex_ocean_15_07_2020.api

import com.oceanbrasil.pokedex_ocean_15_07_2020.model.PokemonsResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query


class PokemonApiRepository {
    private val service: PokemonApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PokemonApiService::class.java)
    }

    fun listarPokemons(
        offset: Long = 0,
        limit: Long = 20
    ) = service.listarPokemons(offset, limit)

    fun visualizarPokemon(
        id: Long
    ) = service.visualizarPokemon(id)
}
