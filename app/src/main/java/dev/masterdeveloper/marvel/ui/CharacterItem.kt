package dev.masterdeveloper.marvel.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import dev.masterdeveloper.marvel.R
import dev.masterdeveloper.marvel.model.Character
import dev.masterdeveloper.marvel.ui.theme.MarvelTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterItem(character: Character, onClick: () -> Unit = { }) {

    val imagePainter = rememberAsyncImagePainter(character.thumbnail.url)
    var isLoading by remember { mutableStateOf(true) }
    var containsErrors by remember { mutableStateOf(false) }
    isLoading = imagePainter.state is AsyncImagePainter.State.Loading

    when (imagePainter.state) {
        is AsyncImagePainter.State.Loading -> isLoading = true
        is AsyncImagePainter.State.Success -> isLoading = false
        is AsyncImagePainter.State.Error -> {
            containsErrors = true
            (imagePainter.state as AsyncImagePainter.State.Error).result.let {
                println(it)
            }
        }
        else -> {}
    }

    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp, pressedElevation = 16.dp),
        onClick = onClick,
        modifier = Modifier
            .width(350.dp)
            .fillMaxHeight(),
    ) {
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier.fillMaxSize()) {
            Image(
                painter = if (!containsErrors) imagePainter else painterResource(id = R.drawable.ic_placeholder),
                contentDescription = character.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .placeholder(
                        isLoading,
                        color = Color.Gray,
                        highlight = PlaceholderHighlight.shimmer()
                    )
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.DarkGray
                        )
                    )
                ))
            Text(
                text = character.name,
                maxLines = 2,
                style = MaterialTheme.typography.displayLarge, color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun CharacterItem_Preview() {
    MarvelTheme {
        CharacterItem(character = Character(
            id = "1011334",
            name = "3-D Man",
            description = "",
            modified = "2014-04-29T14:18:17-0400",
            thumbnail = Character.Thumbnail(
                extension = "jpg",
                path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784"
            ),
            resourceURI = "http://gateway.marvel.com/v1/public/characters/1011334",
            urls = listOf()
        ))
    }
}