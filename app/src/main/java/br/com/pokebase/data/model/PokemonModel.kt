package br.com.pokebase.data.model

import com.google.gson.annotations.SerializedName

data class PokemonCatalogModel(
    val results: List<PokemonSummaryModel>
)

data class PokemonSummaryModel(
    val name: String,
    val url: String
)

data class PokemonDetailModel(
    val id: Int,
    val name: String,
    @SerializedName("sprites")
    val sprite: SpriteModel?,
    val types: List<PokemonTypeItemModel>?
)

data class PokemonTypeItemModel (
    val slot: Int,
    val type: PokemonTypeModel
)

data class PokemonTypeModel (
    @SerializedName("name")
    val typeEnum: TypeEnumModel,
    val url: String
)

data class SpriteModel(
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("other")
    val otherSprites: SpriteOtherModel?
)

data class SpriteOtherModel(
    @SerializedName("official-artwork")
    val officialArtwork: SpriteModel?
)

enum class TypeEnumModel {
    normal, fire, water, electric, grass, ice, fighting, poison, ground, 
    flying, psychic, bug, rock, ghost, dragon, dark, steel, fairy
}
