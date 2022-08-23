package dev.masterdeveloper.marvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.AndroidEntryPoint
import dev.masterdeveloper.marvel.ui.CharactersList
import dev.masterdeveloper.marvel.ui.theme.MarvelTheme
import dev.masterdeveloper.marvel.viewmodel.CharactersViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            MarvelTheme {
                ContentView(viewModel)
            }
        }
    }
}

@Composable
fun ContentView(viewModel: CharactersViewModel) {

    val characters = viewModel.getCharacters().collectAsLazyPagingItems()

    CharactersList(list = characters)
    
}