package dev.masterdeveloper.marvel.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.masterdeveloper.marvel.db.model.RemoteKey

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg key: RemoteKey)

    @Query("""
        SELECT * FROM remote_keys WHERE id = :id
    """)
    suspend fun getRemoteKeyById(id: String): RemoteKey?

    @Query("""
        DELETE FROM remote_keys
    """)
    suspend fun clearAll()

}