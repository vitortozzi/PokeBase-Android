package br.com.pokebase.api

import br.com.pokebase.data.model.PokemonDetail
import br.com.pokebase.data.model.PokemonsResponse
import retrofit2.Response
import java.lang.Exception

class Repository(
    private val apiService: ApiService
) {
    suspend fun getPokemons(limit: Int, offset: Int) : List<PokemonDetail> {
        val response = apiService.getPokemonList(limit, offset)
        if(response.isSuccessful) {
            val pokeDetails: MutableList<PokemonDetail> = mutableListOf()
            response.body()?.results?.forEach {
                apiService.getPokemonDetail(it.name).body()?.let { detail ->
                    pokeDetails.add(detail)
                }
            }
            return pokeDetails
        }
        throw Exception()
    }
}