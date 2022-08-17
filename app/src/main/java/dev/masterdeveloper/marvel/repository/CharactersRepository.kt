package dev.masterdeveloper.marvel.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dev.masterdeveloper.marvel.db.dao.CharactersDao
import dev.masterdeveloper.marvel.remote.CharactersService
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val service: CharactersService,
    private val dao: CharactersDao,
    private val mediator: CharactersRemoteMediator
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getCharacters() = Pager(
        config = PagingConfig(pageSize = 20),
        remoteMediator = mediator
    ) {
        dao.getAllCharacters()
    }

}