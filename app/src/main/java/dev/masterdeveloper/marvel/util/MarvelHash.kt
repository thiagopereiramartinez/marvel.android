package dev.masterdeveloper.marvel.util

import dev.masterdeveloper.marvel.BuildConfig
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class MarvelHash : ReadOnlyProperty<Nothing?, MarvelHash.Data> {

    override fun getValue(thisRef: Nothing?, property: KProperty<*>): Data {
        val ts = Calendar.getInstance().timeInMillis.toString()
        val hash = "${ts}${BuildConfig.MarvelPrivateKey}${BuildConfig.MarvelPublicKey}".md5()

        return Data(ts = ts, hash = hash, pubKey = BuildConfig.MarvelPublicKey)
    }

    data class Data(
        val ts: String,
        val hash: String,
        val pubKey: String
    )

}

fun marvelHash() = MarvelHash()