package br.com.pokebase.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String?,
    val types: String,
    val lastUpdated: Long = System.currentTimeMillis()
) {
}