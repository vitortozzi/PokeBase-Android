package br.com.pokebase.presentation.home

import br.com.pokebase.domain.model.PokemonDetail

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val pokemons: List<PokemonDetail>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
