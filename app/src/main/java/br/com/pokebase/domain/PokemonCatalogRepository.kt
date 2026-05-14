package br.com.pokebase.domain

import br.com.pokebase.domain.model.PokemonDetail

interface PokemonCatalogRepository {
    suspend fun getPokemons(limit: Int, offset: Int): List<PokemonDetail>
}