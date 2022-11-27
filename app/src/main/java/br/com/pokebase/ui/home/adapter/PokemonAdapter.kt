package br.com.pokebase.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.pokebase.capitalizeName
import br.com.pokebase.data.model.PokemonDetail
import br.com.pokebase.databinding.ViewPokemonSimpleItemBinding
import coil.load
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