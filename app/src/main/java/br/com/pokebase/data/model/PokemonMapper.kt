package br.com.pokebase.data.model

import br.com.pokebase.domain.model.*

fun PokemonSummaryModel.toDomain() = PokemonSummary(
    name = name,
    url = url
)

fun PokemonDetailModel.toDomain() = PokemonDetail(
    id = id,
    name = name,
    sprite = sprite?.toDomain(),
    types = types?.map { it.toDomain() }.orEmpty()
)

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
