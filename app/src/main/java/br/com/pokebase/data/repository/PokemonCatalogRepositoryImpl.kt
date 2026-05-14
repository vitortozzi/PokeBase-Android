package br.com.pokebase.data.repository

import br.com.pokebase.data.model.toDomain
import br.com.pokebase.data.remote.ApiService
import br.com.pokebase.domain.PokemonCatalogRepository
import br.com.pokebase.domain.model.PokemonDetail
import javax.inject.Inject

class PokemonCatalogRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PokemonCatalogRepository {
    override suspend fun getPokemons(limit: Int, offset: Int): List<PokemonDetail> {
        val response = apiService.getPokemonList(limit, offset)
        //TODO: This is by far not performatic. I will be migrating this to a better solution on the next days
        // And also that 'orchestration' should be done by a use case on domain layer
        return response.results.map {
            apiService.getPokemonDetail(it.name).toDomain()
        }
    }
}