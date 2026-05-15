package br.com.pokebase.domain

import br.com.pokebase.domain.model.PokemonDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonCatalogUseCase @Inject constructor(
    val repository: PokemonCatalogRepository
) {
    suspend fun getPokemonCatalog(limit: Int, offset: Int) : Flow<List<PokemonDetail>> {
        return repository.getPokemons(limit, offset)
    }
}