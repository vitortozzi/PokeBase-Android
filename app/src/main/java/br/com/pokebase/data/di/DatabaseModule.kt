package br.com.pokebase.data.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import br.com.pokebase.data.local.PokemonDao
import br.com.pokebase.data.local.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): PokemonDatabase {
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java, "pokemon_database.db"
        )
            .fallbackToDestructiveMigration(true)
            .setQueryCallback({ sqlQuery, bindArgs ->
                Log.d("RoomLog", "Query: $sqlQuery Args: $bindArgs")
            }, Executors.newSingleThreadExecutor())
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(pokemonDatabase: PokemonDatabase): PokemonDao {
        return pokemonDatabase.pokemonDao()
    }

}