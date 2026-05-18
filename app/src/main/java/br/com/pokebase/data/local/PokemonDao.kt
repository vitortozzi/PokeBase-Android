package br.com.pokebase.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.pokebase.data.model.FavoriteEntity
import br.com.pokebase.data.model.PokemonEntity
import br.com.pokebase.data.model.PokemonWithFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("""
        SELECT p.*, (f.pokemon_id IS NOT NULL) AS isFavorite 
        FROM pokemon p 
        LEFT JOIN favorites f ON p.id = f.pokemon_id
        ORDER BY p.id ASC
    """)
    fun getAllWithFavorite(): Flow<List<PokemonWithFavorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon WHERE id = :id")
    suspend fun getPokemonById(id: Int): PokemonEntity?

    @Query("DELETE FROM pokemon")
    suspend fun clearAll()

    @Delete
    suspend fun delete(pokemonEntity: PokemonEntity)

    @Query("SELECT MAX(lastUpdated) FROM pokemon")
    suspend fun getLastUpdateTimestamp(): Long?

    @Query("SELECT COUNT(*) FROM pokemon")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE pokemon_id = :pokemonId")
    suspend fun deleteFavorite(pokemonId: Int)
    
    @Query("""
        SELECT p.*, (f.pokemon_id IS NOT NULL) AS isFavorite
        FROM pokemon p
        LEFT JOIN favorites f WHERE f.pokemon_id = p.id
        ORDER BY f.pokemon_id
    """)
    fun getFavorites(): Flow<List<PokemonWithFavorite>>
}