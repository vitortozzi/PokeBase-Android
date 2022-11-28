package br.com.pokebase.ui.home.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.pokebase.R
import br.com.pokebase.capitalizeName
import br.com.pokebase.data.model.PokemonDetail
import br.com.pokebase.data.model.TypeEnum
import br.com.pokebase.databinding.ViewPokemonSimpleItemBinding
import coil.load
import com.google.android.material.chip.Chip
import java.util.*

class SimplePokemonAdapter: RecyclerView.Adapter<SimplePokemonAdapter.ViewHolder>() {

    var pokemons: List<PokemonDetail> = emptyList()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ViewPokemonSimpleItemBinding
        ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(pokemon: PokemonDetail) {
                binding.apply {
                    name.text = pokemon.name.capitalizeName()
                    number.text = "#${pokemon.id}"
                    binding.typeChip.removeAllViews()
                    pokemon.types.forEach {
                        val chip = Chip(binding.root.context)
                        chip.text = it.type.typeEnum.name.capitalizeName()
                        val typeColor = when (it.type.typeEnum) {
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
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            chip.chipBackgroundColor = this.typeChip.context.getColorStateList(
                                typeColor
                            )
                        }
                        chip.setTextColor(
                            when (it.type.typeEnum) {
                                TypeEnum.water,
                                TypeEnum.electric,
                                TypeEnum.grass,
                                TypeEnum.ice,
                                TypeEnum.ground,
                                TypeEnum.flying,
                                TypeEnum.psychic,
                                TypeEnum.bug,
                                TypeEnum.rock,
                                TypeEnum.fairy,
                                TypeEnum.normal,
                                TypeEnum.fire -> Color.BLACK
                                TypeEnum.steel,
                                TypeEnum.dark,
                                TypeEnum.ghost,
                                TypeEnum.dragon,
                                TypeEnum.poison,
                                TypeEnum.fighting -> Color.WHITE
                            }
                        )
                        chip.isChipIconVisible = false
                        chip.isCloseIconVisible = false
                        binding.typeChip.addView(chip)
                    }
                    image.load(pokemon.sprite.otherSprites.officialArtwork.frontDefault) {
                        crossfade(true)
                    }
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ViewPokemonSimpleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(pokemons[position])

    override fun getItemCount(): Int = pokemons.size

}