package br.com.pokebase.data.model

import com.google.gson.annotations.SerializedName

data class PokemonsResponse(
    val results: List<PokemonSummary>
)

data class PokemonSummary(
    val name: String,
    val url: String
)

data class PokemonDetail(
    val id: Int,
    val name: String,
    @SerializedName("sprites")
    val sprite: Sprite
)

data class Sprite(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("other")
    val otherSprites: SpriteOther
)

data class SpriteOther(
    @SerializedName("official-artwork")
    val officialArtwork: Sprite
)