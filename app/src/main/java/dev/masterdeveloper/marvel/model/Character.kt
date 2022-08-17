package dev.masterdeveloper.marvel.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
    val urls: List<Url>
) {

    data class Thumbnail(
        val path: String,
        val extension: String
    )

    data class Url(
        val url: String,
        val type: String
    )

}
