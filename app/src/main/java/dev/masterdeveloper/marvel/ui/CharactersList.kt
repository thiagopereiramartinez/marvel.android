package dev.masterdeveloper.marvel.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dev.masterdeveloper.marvel.viewmodel.CharactersViewModel

@Composable
fun CharactersList(navController: NavController, viewModel: CharactersViewModel) {

    val list = viewModel.getCharacters().collectAsLazyPagingItems()

    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(list) { character ->
            character ?: return@items

            CharacterItem(character = character) {
                navController.navigate("details/${character.id}")
            }
        }
    }
}