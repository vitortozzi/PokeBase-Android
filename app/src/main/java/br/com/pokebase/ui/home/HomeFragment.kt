package br.com.pokebase.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.pokebase.databinding.FragmentHomeBinding
import br.com.pokebase.ui.home.adapter.SimplePokemonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is PokeBaseHomeViewState.Error -> TODO()
                PokeBaseHomeViewState.Loading -> showLoading()
                is PokeBaseHomeViewState.PokemonsLoaded -> {
                    hideLoading()
                    val adapter = SimplePokemonAdapter()
                    adapter.pokemons = it.pokemons
                    binding.recycler.adapter = adapter
                }
            }
        }
    }

    private fun hideLoading() {
        binding.animationView.visibility = View.GONE
    }

    private fun showLoading() {
        binding.animationView.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}