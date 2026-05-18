package br.com.pokebase.data.repository

import br.com.pokebase.data.local.PokemonDao
import br.com.pokebase.domain.repository.PokemonFavoriteRepository
import javax.inject.Inject
import br.com.pokebase.data.model.FavoriteEntity
import br.com.pokebase.data.model.toDomain
import br.com.pokebase.domain.model.PokemonDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonFavoriteRepositoryImpl @Inject constructor(
    val pokemonDao: PokemonDao
) : PokemonFavoriteRepository {
    override suspend fun favoritePokemon(id: Int, isFavorite: Boolean) {
        if (isFavorite) {
            pokemonDao.insertFavorite(FavoriteEntity(id))
        } else {
            pokemonDao.deleteFavorite(id)
        }
    }

    override fun getFavoritedPokemons(): Flow<List<PokemonDetail>> {
        return pokemonDao.getFavorites().map { list -> list.map { it.toDomain() } }
    }
}
