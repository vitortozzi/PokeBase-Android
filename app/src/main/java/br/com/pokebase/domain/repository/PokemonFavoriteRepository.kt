package br.com.pokebase.domain.repository

import br.com.pokebase.domain.model.PokemonDetail
import kotlinx.coroutines.flow.Flow

interface PokemonFavoriteRepository {
    suspend fun favoritePokemon(id: Int, isFavorite: Boolean)
    fun getFavoritedPokemons(): Flow<List<PokemonDetail>>
}