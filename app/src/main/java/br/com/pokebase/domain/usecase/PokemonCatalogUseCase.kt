package br.com.pokebase.domain.usecase

import br.com.pokebase.domain.repository.PokemonCatalogRepository
import br.com.pokebase.domain.model.PokemonDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonCatalogUseCase @Inject constructor(
    private val repository: PokemonCatalogRepository
) {
    suspend fun getPokemonCatalog(limit: Int, offset: Int) : Flow<List<PokemonDetail>> {
        return repository.getPokemons(limit, offset)
    }
}