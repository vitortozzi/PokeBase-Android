package br.com.pokebase.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pokebase.domain.FavoritePokemonUseCase
import br.com.pokebase.domain.PokemonCatalogUseCase
import br.com.pokebase.domain.model.PokemonDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val catalogUseCase: PokemonCatalogUseCase,
    private val favoriteUseCase: FavoritePokemonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadCatalog()
    }

    fun loadCatalog() {
        viewModelScope.launch {
            _uiState.update { HomeUiState.Loading }
            try {
                // TODO: fixed offset and limit for tests purposes. Need to improve it
                catalogUseCase.getPokemonCatalog(151, 0).collect { pokemonList ->
                    _uiState.update { HomeUiState.Success(pokemonList) }
                }
            } catch (e: Exception) {
                _uiState.update { HomeUiState.Error(message = e.message ?: "Unknown error") }
            }
        }
    }

    fun favorite(pokemon: PokemonDetail, favorite: Boolean) {
        viewModelScope.launch {
            try {
                favoriteUseCase.favoritePokemon(pokemon.id, favorite)
            } catch (e: Exception) {
                // In this sealed state architecture, error messages for specific actions 
                // could be handled via a separate Channel/Flow for "Events" (Snackbars, Toast).
                // For now, we log the error as the main state change is reactive via DB.
                e.printStackTrace()
            }
        }
    }
}
