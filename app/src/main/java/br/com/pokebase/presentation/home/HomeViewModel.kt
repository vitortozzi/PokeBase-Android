package br.com.pokebase.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pokebase.domain.PokemonCatalogRepository
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
    private val repository: PokemonCatalogRepository
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
                //TODO: fixed offset and limit for tests porpuses. Need to improve it
                val pokeDetails = repository.getPokemons(151, 0)
                _uiState.update { it.copy(isLoading = false, pokemons = pokeDetails) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Unknown error") }
            }
        }
    }
}
