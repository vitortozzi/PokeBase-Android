package br.com.pokebase.interactor

import br.com.pokebase.data.model.PokemonSummary

interface GetPokemonsUseCase {
    fun getPokemons(): List<PokemonSummary>
}

//class GetPokemonsUseCaseImpl(): GetPokemonsUseCase {
//
//    override fun getPokemons(): List<Pokemon> {
//
//    }
//
//}