package br.com.pokebase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.pokebase.data.model.FavoriteEntity
import br.com.pokebase.data.model.PokemonEntity

@Database(entities = [PokemonEntity::class, FavoriteEntity::class], version = 2)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}