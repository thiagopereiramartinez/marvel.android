package dev.masterdeveloper.marvel.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.masterdeveloper.marvel.db.Database
import dev.masterdeveloper.marvel.db.dao.CharactersDao
import dev.masterdeveloper.marvel.db.dao.RemoteKeyDao
import dev.masterdeveloper.marvel.db.model.RemoteKey
import dev.masterdeveloper.marvel.model.Character
import dev.masterdeveloper.marvel.remote.CharactersService
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator @Inject constructor(
    private val database: Database,
    private val service: CharactersService
) : RemoteMediator<Int, Character>() {

    private val dao: CharactersDao
        get() = database.charactersDao()

    private val remoteKeyDao: RemoteKeyDao
        get() = database.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val id = state.lastItemOrNull()?.id ?: return MediatorResult.Success(endOfPaginationReached = true)
                    val remoteKey = remoteKeyDao.getRemoteKeyById(id)
                    if (remoteKey?.nextKey == null) return MediatorResult.Success(endOfPaginationReached = true)

                    remoteKey.nextKey
                }
            }


            val response = service.getCharacters(offset = loadKey, limit = state.config.pageSize)
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.clearAll()
                    dao.clearAll()
                }
                remoteKeyDao.insert(*response.data.results.map { RemoteKey(id = it.id, nextKey = response.data.offset + response.data.count) }.toTypedArray())
                dao.insert(*response.data.results.toTypedArray())
            }

            MediatorResult.Success(
                endOfPaginationReached = (response.data.offset + response.data.count) >= response.data.total
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

}