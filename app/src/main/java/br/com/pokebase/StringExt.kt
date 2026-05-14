package br.com.pokebase

import java.util.*

fun String.capitalizeName() : String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(
        Locale.getDefault()) else it.toString() }
}