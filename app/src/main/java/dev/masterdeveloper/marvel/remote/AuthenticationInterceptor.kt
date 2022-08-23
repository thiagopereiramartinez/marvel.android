package dev.masterdeveloper.marvel.remote

import dev.masterdeveloper.marvel.util.marvelHash
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val auth by marvelHash()

        val url = chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter("apikey", auth.pubKey)
            .addQueryParameter("ts", auth.ts)
            .addQueryParameter("hash", auth.hash)
            .build()

        return chain.proceed(chain.request().newBuilder().url(url).build())
    }

}