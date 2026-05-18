package br.com.pokebase.domain.usecase

import br.com.pokebase.domain.model.PokemonDetail
import br.com.pokebase.domain.repository.PokemonFavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritedPokemonsUseCase @Inject constructor(
    private val pokemonFavoriteRepository: PokemonFavoriteRepository
) {
    fun getFavoritedPokemons(): Flow<List<PokemonDetail>>{
        return pokemonFavoriteRepository.getFavoritedPokemons()
    }
}