package br.com.pokebase.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pokebase.api.Repository
import br.com.pokebase.data.model.PokemonDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val viewState = MutableLiveData<PokeBaseHomeViewState>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    init {
        reloadPokemons()
    }

    private fun reloadPokemons() {
        viewModelScope.launch {
            viewState.postValue(PokeBaseHomeViewState.Loading)
            val pokeDetails = repository.getPokemons(151,0)
            viewState.postValue(PokeBaseHomeViewState.PokemonsLoaded(pokeDetails))
        }
    }
}

sealed class PokeBaseHomeViewState {
    data class PokemonsLoaded(val pokemons: List<PokemonDetail>): PokeBaseHomeViewState()
    object Loading: PokeBaseHomeViewState()
    data class Error(val exception: Exception): PokeBaseHomeViewState()
}