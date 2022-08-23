package dev.masterdeveloper.marvel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.masterdeveloper.marvel.model.Character
import dev.masterdeveloper.marvel.repository.CharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _character: MutableStateFlow<Character?> = MutableStateFlow(null)
    val character: StateFlow<Character?>
        get() = _character

    fun getCharacters() =
        charactersRepository.getCharacters().cachedIn(viewModelScope)

    fun readCharacter(id: String) = viewModelScope.launch {
        _character.emit(charactersRepository.getCharacter(id))
    }
}