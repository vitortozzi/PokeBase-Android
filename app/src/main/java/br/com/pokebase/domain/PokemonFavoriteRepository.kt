package br.com.pokebase.domain

interface PokemonFavoriteRepository {
    suspend fun favoritePokemon(id: Int, isFavorite: Boolean)
}