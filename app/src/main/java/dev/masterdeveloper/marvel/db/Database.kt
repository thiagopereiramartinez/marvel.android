package dev.masterdeveloper.marvel.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.masterdeveloper.marvel.db.dao.CharactersDao
import dev.masterdeveloper.marvel.db.dao.RemoteKeyDao
import dev.masterdeveloper.marvel.db.model.RemoteKey
import dev.masterdeveloper.marvel.model.Character

@Database(
    entities = [
        Character::class,
        RemoteKey::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
    abstract fun remoteKeysDao(): RemoteKeyDao

}