package br.com.pokebase.presentation.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.pokebase.presentation.common.PokemonItem

@Composable
fun FavoritesRoute(
    modifier: Modifier = Modifier,
    viewModel : FavoriteViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    FavoriteScreen(
        uiState,
        onTapFavorite = { id, isFavorite ->
            viewModel.tapFavorite(id, isFavorite)
        },
        modifier = modifier
    )
}

@Composable
fun FavoriteScreen(
    uiState: FavoritesUiState,
    onTapFavorite: (id: Int, isFavorite: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = colorScheme.background
    ) {
        when(uiState) {
            is FavoritesUiState.Error -> {
                Text("Error")
            }
            FavoritesUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is FavoritesUiState.Success -> {
                Column {
                    Text(
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp ),
                        text = "My Favorite Pokémons",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    if(uiState.favoritedPokemons.isEmpty()) {
                        Text(
                            modifier = Modifier.padding(start = 16.dp, 24.dp),
                            style = MaterialTheme.typography.bodyLarge,
                            text = "There are no favorite Pokémons.")
                    } else {
                        LazyColumn(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(
                                uiState.favoritedPokemons,
                                key = {
                                    it.id
                                }) { pokemon ->
                                PokemonItem(
                                    pokemon = pokemon,
                                    onFavoriteClick = {
                                        onTapFavorite(
                                            pokemon.id,
                                            it
                                        )
                                    },
                                    modifier = Modifier.animateItem()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
