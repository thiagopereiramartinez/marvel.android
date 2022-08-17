package dev.masterdeveloper.marvel.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.masterdeveloper.marvel.remote.AuthenticationInterceptor
import dev.masterdeveloper.marvel.remote.CharactersService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(authenticationInterceptor: AuthenticationInterceptor, loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient
            .Builder()
            .addInterceptor(authenticationInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun providesAuthenticationInterceptor() = AuthenticationInterceptor()

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun providesGsonFactory() = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory) =
        Retrofit
            .Builder()
            .baseUrl("https://gateway.marvel.com:443/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    @Singleton
    fun providesCharactersService(retrofit: Retrofit) = retrofit.create(CharactersService::class.java)

}