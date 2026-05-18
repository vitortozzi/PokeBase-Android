package br.com.pokebase.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.pokebase.capitalizeName
import br.com.pokebase.domain.model.PokemonDetail
import br.com.pokebase.domain.model.PokemonTypeItem
import br.com.pokebase.presentation.home.model.toColorRes
import br.com.pokebase.presentation.home.model.toOnColorRes
import coil3.compose.AsyncImage

@Composable
fun PokemonItem(
    pokemon: PokemonDetail,
    onFavoriteClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
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
