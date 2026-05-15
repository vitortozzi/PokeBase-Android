package br.com.pokebase.domain

import br.com.pokebase.domain.model.PokemonDetail
import kotlinx.coroutines.flow.Flow

interface PokemonCatalogRepository {
    suspend fun getPokemons(limit: Int, offset: Int): Flow<List<PokemonDetail>>
}