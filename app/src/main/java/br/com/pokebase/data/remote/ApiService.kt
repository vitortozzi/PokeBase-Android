package br.com.pokebase.data.remote

import br.com.pokebase.data.model.PokemonDetailModel
import br.com.pokebase.data.model.PokemonCatalogModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int): PokemonCatalogModel

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name: String): PokemonDetailModel
}