package br.com.pokebase.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.pokebase.data.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    fun getAll(): Flow<List<PokemonEntity>>

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
}