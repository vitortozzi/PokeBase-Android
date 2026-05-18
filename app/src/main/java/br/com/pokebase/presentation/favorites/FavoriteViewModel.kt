package br.com.pokebase.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pokebase.domain.usecase.FavoritePokemonUseCase
import br.com.pokebase.domain.usecase.GetFavoritedPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritedPokemonsUseCase: GetFavoritedPokemonsUseCase,
    private val favoriteUseCase: FavoritePokemonUseCase
) : ViewModel() {


    private var _uiState = MutableStateFlow<FavoritesUiState>(FavoritesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            getFavoritedPokemonsUseCase.getFavoritedPokemons().collect { favoritePokemons ->
                _uiState.update { FavoritesUiState.Success(favoritePokemons) }
            }
        }
    }

    fun tapFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            try {
                favoriteUseCase.favoritePokemon(id, isFavorite)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}