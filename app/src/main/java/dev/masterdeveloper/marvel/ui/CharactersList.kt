package dev.masterdeveloper.marvel.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import dev.masterdeveloper.marvel.model.Character
import dev.masterdeveloper.marvel.ui.theme.MarvelTheme

@Composable
fun CharactersList(list: LazyPagingItems<Character>) {
    LazyColumn {
        items(list) { character ->
            character ?: return@items

            Text(character.name)
        }
    }
}

@Preview
@Composable
fun CharactersList_Preview() {
    MarvelTheme {
        /*CharactersList((0..10).map { i ->
            Character(
                id = "",
                name = "Hero #$i",
                description = "Description of Hero #$i",
                modified = "",
                thumbnail = Character.Thumbnail(
                    path = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                    extension = "jpg"
                ),
                resourceURI = "",
                urls = listOf(Character.Url(
                    url = "",
                    type = ""
                ))
            )
        })*/
    }
}