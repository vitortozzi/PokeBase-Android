package br.com.pokebase.data.repository

import br.com.pokebase.GetPokemonListQuery
import br.com.pokebase.data.model.SpriteModel
import br.com.pokebase.data.model.toDomain
import br.com.pokebase.domain.PokemonCatalogRepository
import br.com.pokebase.domain.model.PokemonDetail
import br.com.pokebase.domain.model.PokemonType
import br.com.pokebase.domain.model.PokemonTypeItem
import br.com.pokebase.domain.model.Sprite
import br.com.pokebase.domain.model.TypeEnum
import com.apollographql.apollo.ApolloClient
import com.google.gson.Gson
import javax.inject.Inject

class PokemonCatalogRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val gson: Gson
) : PokemonCatalogRepository {
    override suspend fun getPokemons(limit: Int, offset: Int): List<PokemonDetail> {
        val response = apolloClient.query(GetPokemonListQuery(limit = limit, offset = offset)).execute()
        return response.data?.pokemon?.map {
            PokemonDetail(
                id = it.id,
                name = it.name,
                types = it.pokemontypes.map { type -> PokemonTypeItem(
                    slot = type.slot,
                    type = PokemonType(
                        typeEnum = TypeEnum.valueOf(type.type?.name ?: TypeEnum.normal.name),
                        url = ""
                    )
                ) 
                },
                sprite = mapSprite(it.pokemonsprites.firstOrNull()?.sprites)
            )
        } ?: emptyList()
        
    }

    private fun mapSprite(spritesJson: Any?): Sprite? {
        return try {
            val jsonString = gson.toJson(spritesJson)
            gson.fromJson(jsonString, SpriteModel::class.java).toDomain()
        } catch (_: Exception) {
            null
        }
    }
}