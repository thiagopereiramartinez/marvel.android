package dev.masterdeveloper.marvel.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import dev.masterdeveloper.marvel.model.Character

@Composable
fun CharactersList(list: LazyPagingItems<Character>) {

    val context = LocalContext.current

    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(list) { character ->
            character ?: return@items

            CharacterItem(character = character) {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(character.urls.first().url)
                }
                context.startActivity(intent)
            }
        }
    }
}