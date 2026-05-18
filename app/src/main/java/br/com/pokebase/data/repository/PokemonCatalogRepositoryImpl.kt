package br.com.pokebase.data.repository

import br.com.pokebase.GetPokemonListQuery
import br.com.pokebase.data.local.PokemonDao
import br.com.pokebase.data.model.PokemonEntity
import br.com.pokebase.data.model.SpriteModel
import br.com.pokebase.data.model.TypeEnumModel
import br.com.pokebase.data.model.toDomain
import br.com.pokebase.domain.repository.PokemonCatalogRepository
import br.com.pokebase.domain.model.PokemonDetail
import com.apollographql.apollo.ApolloClient
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonCatalogRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val gson: Gson,
    private val pokemonDao: PokemonDao,
) : PokemonCatalogRepository {

    private val CACHE_TIMEOUT = 24 * 60 * 60 * 1000L

    override suspend fun getPokemons(limit: Int, offset: Int): Flow<List<PokemonDetail>> {
        val count = pokemonDao.getCount()
        val lastUpdate = pokemonDao.getLastUpdateTimestamp() ?: 0L
        val isExpired = System.currentTimeMillis() - lastUpdate > CACHE_TIMEOUT

        if (count < (offset + limit) || isExpired) {
            fetchAndSavePokemons(limit, offset)
        }

        return pokemonDao.getAllWithFavorite().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    private suspend fun fetchAndSavePokemons(limit: Int, offset: Int) {
        try {
            val response = apolloClient.query(GetPokemonListQuery(limit = limit, offset = offset)).execute()
            val pokemonList = response.data?.pokemon ?: emptyList()

            val entities = pokemonList.map {
                PokemonEntity(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.pokemonsprites.firstOrNull()?.sprites?.let { spritesJson ->
                        val spriteModel = mapSprite(spritesJson)
                        spriteModel?.otherSprites?.officialArtwork?.frontDefault
                    },
                    types = it.pokemontypes.joinToString(",") { type ->
                        type.type?.name ?: TypeEnumModel.normal.name
                    }
                )
            }

            if (entities.isNotEmpty()) {
                pokemonDao.insertAll(entities)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun mapSprite(spritesJson: Any?): SpriteModel? {
        return try {
            val jsonString = gson.toJson(spritesJson)
            gson.fromJson(jsonString, SpriteModel::class.java)
        } catch (_: Exception) {
            null
        }
    }
}