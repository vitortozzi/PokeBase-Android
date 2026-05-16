package br.com.pokebase.domain

import javax.inject.Inject

class FavoritePokemonUseCase @Inject constructor(
    val repository: PokemonFavoriteRepository
) {
    suspend fun favoritePokemon(id: Int, isFavorite: Boolean) {
        repository.favoritePokemon(id, isFavorite)
    }
}