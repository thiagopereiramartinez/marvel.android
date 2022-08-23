package dev.masterdeveloper.marvel.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.masterdeveloper.marvel.model.Character

object Converters {

    @TypeConverter
    fun fromThumbnailToString(thumbnail: Character.Thumbnail) =
        Gson().toJson(thumbnail)

    @TypeConverter
    fun fromStringToThumbnail(thumbnail: String) =
        Gson().fromJson<Character.Thumbnail>(thumbnail, object : TypeToken<Character.Thumbnail>() { }.type)

    @TypeConverter
    fun fromListCharacterUrlToString(url: List<Character.Url>) =
        Gson().toJson(url)

    @TypeConverter
    fun fromStringToListCharacterUrl(url: String) =
        Gson().fromJson<List<Character.Url>>(url, object : TypeToken<List<Character.Url>>() { }.type)

}