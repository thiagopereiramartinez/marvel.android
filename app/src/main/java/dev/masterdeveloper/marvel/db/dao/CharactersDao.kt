package dev.masterdeveloper.marvel.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.masterdeveloper.marvel.model.Character

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg character: Character)

    @Query("""
        SELECT * FROM characters
    """)
    fun getAllCharacters(): PagingSource<Int, Character>

    @Query("""
        SELECT * FROM characters WHERE id = :id
    """)
    suspend fun getCharacter(id: String): Character

    @Query("""
        DELETE FROM characters
    """)
    suspend fun clearAll()

}