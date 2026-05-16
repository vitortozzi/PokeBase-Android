package br.com.pokebase.data.model

import br.com.pokebase.domain.model.*

fun PokemonTypeItemModel.toDomain() = PokemonTypeItem(
    slot = slot,
    type = type.toDomain()
)

fun PokemonTypeModel.toDomain() = PokemonType(
    typeEnum = typeEnum.toDomain(),
    url = url
)

fun SpriteModel.toDomain() = Sprite(
    frontDefault = frontDefault,
    otherSprites = otherSprites?.toDomain()
)

fun SpriteOtherModel.toDomain() = SpriteOther(
    officialArtwork = officialArtwork?.let { 
        SpriteArtwork(frontDefault = it.frontDefault) 
    }
)

fun TypeEnumModel.toDomain() = TypeEnum.valueOf(this.name)

fun PokemonEntity.toDomain(isFavorite: Boolean = false) = PokemonDetail(
    id = id,
    name = name,
    sprite = Sprite(
        frontDefault = null,
        otherSprites = SpriteOther(
            officialArtwork = SpriteArtwork(frontDefault = imageUrl)
        )
    ),
    types = types.split(",").filter { it.isNotEmpty() }.mapIndexed { index, typeName ->
        PokemonTypeItem(
            slot = index + 1,
            type = PokemonType(
                typeEnum = TypeEnum.valueOf(typeName),
                url = ""
            )
        )
    },
    isFavorite = isFavorite
)

fun PokemonWithFavorite.toDomain() = pokemonEntity.toDomain(isFavorite = isFavorite)
