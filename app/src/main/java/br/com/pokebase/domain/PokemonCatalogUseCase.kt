package br.com.pokebase.domain

import br.com.pokebase.domain.model.PokemonDetail
import javax.inject.Inject

class PokemonCatalogUseCase @Inject constructor(
    val repository: PokemonCatalogRepository
) {
    suspend fun getPokemonCatalog(limit: Int, offset: Int) : List<PokemonDetail> {
        return repository.getPokemons(limit, offset)
    }
}