package br.com.pokebase.data.di

import br.com.pokebase.data.repository.PokemonCatalogRepositoryImpl
import br.com.pokebase.domain.PokemonCatalogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class PokemonCatalogRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPokemonCatalogRepository(repository: PokemonCatalogRepositoryImpl): PokemonCatalogRepository

}