package br.com.pokebase.presentation.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import br.com.pokebase.R
import br.com.pokebase.capitalizeName
import br.com.pokebase.domain.model.PokemonDetail
import br.com.pokebase.domain.model.PokemonType
import br.com.pokebase.domain.model.PokemonTypeItem
import br.com.pokebase.domain.model.Sprite
import br.com.pokebase.domain.model.SpriteArtwork
import br.com.pokebase.domain.model.SpriteOther
import br.com.pokebase.domain.model.TypeEnum
import br.com.pokebase.presentation.home.model.toColorRes
import br.com.pokebase.presentation.home.model.toOnColorRes
import coil3.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    HomeScreen(
        uiState = uiState,
        onRetry = { viewModel.loadCatalog() },
        onFavoriteClick = { pokemon, isFavorite ->
            viewModel.favorite(pokemon, isFavorite)
        },
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onRetry: () -> Unit,
    onFavoriteClick: (PokemonDetail, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        targetState = uiState,
        transitionSpec = {
            fadeIn(animationSpec = tween(500)) togetherWith
                    fadeOut(animationSpec = tween(500))
        },
        contentKey = { it::class },
        label = "HomeScreenTransition"
    ) { targetState ->
        Box(modifier = modifier.fillMaxSize()) {
            when (targetState) {
                is HomeUiState.Loading -> {
                    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.pokeball_loading_animation))
                    LottieAnimation(
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.Center)
                    )
                }

                is HomeUiState.Error -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = targetState.message,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )
                        Button(
                            onClick = onRetry
                        ) {
                            Text("Try Again")
                        }
                    }
                }

                is HomeUiState.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(
                            targetState.pokemons, key = {
                                it.id
                            }) { pokemon ->
                            PokemonItem(
                                pokemon = pokemon,
                                onFavoriteClick = { onFavoriteClick(pokemon, it) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonItem(
    pokemon: PokemonDetail,
    onFavoriteClick: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        val typeColor = pokemon.types.firstOrNull()?.type?.typeEnum?.let {
            colorResource(id = it.toColorRes())
        } ?: MaterialTheme.colorScheme.surfaceVariant

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        0.0f to typeColor,
                        1.0f to MaterialTheme.colorScheme.surfaceVariant,
                        start = Offset(0f, 0f),
                        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = pokemon.sprite?.otherSprites?.officialArtwork?.frontDefault,
                        contentDescription = pokemon.name,
                        modifier = Modifier.size(80.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "#${pokemon.id.toString().padStart(3, '0')}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                    Text(
                        text = pokemon.name.capitalizeName(),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        pokemon.types.forEach { typeItem ->
                            TypeChip(typeItem)
                        }
                    }
                }

                IconToggleButton(
                    checked = pokemon.isFavorite,
                    onCheckedChange = onFavoriteClick
                ) {
                    Icon(
                        imageVector = if (pokemon.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = if (pokemon.isFavorite) "Remove from favorites" else "Add to favorites",
                        tint = if (pokemon.isFavorite) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun TypeChip(typeItem: PokemonTypeItem) {
    Surface(
        color = colorResource(id = typeItem.type.typeEnum.toColorRes()),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = typeItem.type.typeEnum.name.capitalizeName().uppercase(),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            color = colorResource(id = typeItem.type.typeEnum.toOnColorRes())
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonItemPreview() {
    val samplePokemon = PokemonDetail(
        id = 1,
        name = "bulbasaur",
        sprite = Sprite(
            frontDefault = null,
            otherSprites = SpriteOther(
                officialArtwork = SpriteArtwork(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                )
            )
        ),
        types = listOf(
            PokemonTypeItem(1, PokemonType(TypeEnum.grass, "")),
            PokemonTypeItem(2, PokemonType(TypeEnum.poison, ""))
        )
    )

    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            PokemonItem(
                pokemon = samplePokemon,
                onFavoriteClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonItemFavoritePreview() {
    val samplePokemon = PokemonDetail(
        id = 1,
        name = "bulbasaur",
        sprite = Sprite(
            frontDefault = null,
            otherSprites = SpriteOther(
                officialArtwork = SpriteArtwork(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                )
            )
        ),
        types = listOf(
            PokemonTypeItem(1, PokemonType(TypeEnum.grass, "")),
            PokemonTypeItem(2, PokemonType(TypeEnum.poison, ""))
        ),
        isFavorite = true
    )

    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            PokemonItem(
                pokemon = samplePokemon,
                onFavoriteClick = {}
            )
        }
    }
}
