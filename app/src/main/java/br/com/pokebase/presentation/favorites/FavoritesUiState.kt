package br.com.pokebase.presentation.favorites

import br.com.pokebase.domain.model.PokemonDetail

sealed interface FavoritesUiState {
    data class Success(val favoritedPokemons: List<PokemonDetail>) : FavoritesUiState
    data class Error(val message: String) : FavoritesUiState
    object Loading : FavoritesUiState
}