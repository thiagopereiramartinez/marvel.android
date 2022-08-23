package dev.masterdeveloper.marvel.util

import android.net.Uri

infix fun Uri.appendQuery(query: Map<String, String>) = Uri.Builder().let {
    it.scheme(scheme)
    it.authority(authority)
    it.path(path)
    query.forEach { key, value ->
      it.appendQueryParameter(key, value)
    }
    it
}.build()