package br.com.pokebase.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    private val name: String,
    private val sprites: Sprite
)

data class Sprite(
    @SerializedName("front_default")
    private val frontDefault: String
)