package br.com.pokebase.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pokebase.domain.PokemonCatalogUseCase
import br.com.pokebase.domain.model.PokemonDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = false,
    val pokemons: List<PokemonDetail> = emptyList(),
    val errorMessage: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val catalogUseCase: PokemonCatalogUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadCatalog()
    }

    fun loadCatalog() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                //TODO: fixed offset and limit for tests purposes. Need to improve it
                catalogUseCase.getPokemonCatalog(151, 0).collect { pokemonList ->
                    _uiState.update { it.copy(isLoading = false, pokemons = pokemonList) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Unknown error") }
            }
        }
    }
}
