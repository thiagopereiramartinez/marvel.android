package dev.masterdeveloper.marvel.remote

import dev.masterdeveloper.marvel.BuildConfig
import dev.masterdeveloper.marvel.util.md5
import okhttp3.*
import java.util.*

class AuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val ts = Calendar.getInstance().timeInMillis.toString()

        val hash = "${ts}${BuildConfig.MarvelPrivateKey}${BuildConfig.MarvelPublicKey}".md5()
        val url = chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter("apikey", BuildConfig.MarvelPublicKey)
            .addQueryParameter("ts", ts)
            .addQueryParameter("hash", hash)
            .build()

        return chain.proceed(chain.request().newBuilder().url(url).build())
    }

}