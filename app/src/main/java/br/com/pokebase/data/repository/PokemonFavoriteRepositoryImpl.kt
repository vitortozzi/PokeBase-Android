package br.com.pokebase.data.repository

import br.com.pokebase.data.local.PokemonDao
import br.com.pokebase.domain.PokemonFavoriteRepository
import javax.inject.Inject
import br.com.pokebase.data.model.FavoriteEntity

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
}
