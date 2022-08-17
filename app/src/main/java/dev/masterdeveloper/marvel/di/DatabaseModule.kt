package dev.masterdeveloper.marvel.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.masterdeveloper.marvel.db.Database
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, Database::class.java, "marvel.db").build()

    @Provides
    @Singleton
    fun providesCharactersDao(database: Database) = database.charactersDao()

    @Provides
    @Singleton
    fun providesRemoteKeyDao(database: Database) = database.remoteKeysDao()

}