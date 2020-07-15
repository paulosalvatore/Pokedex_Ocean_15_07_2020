package com.oceanbrasil.pokedex_ocean_15_07_2020.model

data class PokemonsResult(
    val count: Long,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
) {
    fun getId() = url.split("/")[6].toLong()
}

data class PokemonInfoResult(
    val name: String,
    val sprites: PokemonSprites
)

data class PokemonSprites(
    val front_default: String
)
