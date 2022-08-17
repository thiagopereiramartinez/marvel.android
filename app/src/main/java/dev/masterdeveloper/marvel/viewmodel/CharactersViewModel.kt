package dev.masterdeveloper.marvel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.masterdeveloper.marvel.repository.CharactersRepository
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    fun getCharacters() = charactersRepository.getCharacters().flow.cachedIn(viewModelScope)

}