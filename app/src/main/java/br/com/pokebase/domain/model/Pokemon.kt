package br.com.pokebase.domain.model

data class PokemonCatalog(
    val results: List<PokemonSummary>
)

data class PokemonSummary(
    val name: String,
    val url: String
)

data class PokemonDetail(
    val id: Int,
    val name: String,
    val sprite: Sprite?,
    val types: List<PokemonTypeItem>,
    val isFavorite: Boolean = false
)

data class PokemonTypeItem(
    val slot: Int,
    val type: PokemonType
)

data class PokemonType(
    val typeEnum: TypeEnum,
    val url: String
)

data class Sprite(
    val frontDefault: String?,
    val otherSprites: SpriteOther?
)

data class SpriteOther(
    val officialArtwork: SpriteArtwork?
)

data class SpriteArtwork(
    val frontDefault: String?
)

enum class TypeEnum {
    normal, fire, water, electric, grass, ice, fighting, poison, ground, 
    flying, psychic, bug, rock, ghost, dragon, dark, steel, fairy
}
