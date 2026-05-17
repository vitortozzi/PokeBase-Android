package br.com.pokebase.presentation.home.model

import androidx.annotation.ColorRes
import br.com.pokebase.R
import br.com.pokebase.domain.model.TypeEnum

@ColorRes
fun TypeEnum.toColorRes(): Int {
    return when (this) {
        TypeEnum.normal -> R.color.normal
        TypeEnum.fire -> R.color.fire
        TypeEnum.water -> R.color.water
        TypeEnum.electric -> R.color.electric
        TypeEnum.grass -> R.color.grass
        TypeEnum.ice -> R.color.ice
        TypeEnum.fighting -> R.color.fighting
        TypeEnum.poison -> R.color.poison
        TypeEnum.ground -> R.color.ground
        TypeEnum.flying -> R.color.flying
        TypeEnum.psychic -> R.color.psychic
        TypeEnum.bug -> R.color.bug
        TypeEnum.rock -> R.color.rock
        TypeEnum.ghost -> R.color.ghost
        TypeEnum.dragon -> R.color.dragon
        TypeEnum.dark -> R.color.dark
        TypeEnum.steel -> R.color.steel
        TypeEnum.fairy -> R.color.fairy
    }
}

@ColorRes
fun TypeEnum.toOnColorRes(): Int {
    return when (this) {
        TypeEnum.electric, TypeEnum.ice, TypeEnum.normal -> R.color.black
        else -> R.color.white
    }
}
