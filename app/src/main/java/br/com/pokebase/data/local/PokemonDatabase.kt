package br.com.pokebase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.pokebase.data.model.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}