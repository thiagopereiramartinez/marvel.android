package dev.masterdeveloper.marvel.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import dev.masterdeveloper.marvel.R
import dev.masterdeveloper.marvel.model.Character
import dev.masterdeveloper.marvel.ui.theme.MarvelTheme
import dev.masterdeveloper.marvel.viewmodel.CharactersViewModel

@Composable
fun CharacterDetails(navController: NavController, viewModel: CharactersViewModel, characterId: String) {

    viewModel.readCharacter(characterId)
    val character = viewModel.character.collectAsState(null).value ?: return

    CharacterDetailsScaffold(character = character) {
        navController.popBackStack()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScaffold(character: Character, onBackPress: () -> Unit = {}) {
    Column(modifier = Modifier.fillMaxSize()) {
        MediumTopAppBar(
            title = { Text(text = character.name) },
            navigationIcon = {
                IconButton(onClick = onBackPress) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(R.string.voltar))
                }
            }
        )

        var selectedTab by remember { mutableStateOf(CharacterDetailsTab.COMICS) }

        TabRow(selectedTabIndex = selectedTab.ordinal) {
            CharacterDetailsTab.values().forEach { tab ->
                Tab(
                    selected = selectedTab == tab,
                    onClick = { selectedTab = tab },
                    text = { Text(text = stringResource(tab.titleResId)) }
                )
            }
        }
    }
}

enum class CharacterDetailsTab(@StringRes val titleResId: Int) {
    COMICS(R.string.comics), SERIES(R.string.series), STORIES(R.string.stories), EVENTS(R.string.events)
}

@Preview
@Composable
fun CharacterDetailsScaffold_Preview() {
    MarvelTheme {
        CharacterDetailsScaffold(character = Character(
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
