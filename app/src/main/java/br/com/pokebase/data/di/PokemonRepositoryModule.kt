package br.com.pokebase.data.di

import br.com.pokebase.data.repository.PokemonCatalogRepositoryImpl
import br.com.pokebase.data.repository.PokemonFavoriteRepositoryImpl
import br.com.pokebase.domain.PokemonCatalogRepository
import br.com.pokebase.domain.PokemonFavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class PokemonRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPokemonCatalogRepository(repository: PokemonCatalogRepositoryImpl): PokemonCatalogRepository

    @Binds
    @Singleton
    abstract fun bindPokemonFavoriteRepository(repository: PokemonFavoriteRepositoryImpl): PokemonFavoriteRepository

}