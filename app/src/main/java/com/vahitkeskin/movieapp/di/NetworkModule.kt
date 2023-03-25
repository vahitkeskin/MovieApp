package com.vahitkeskin.movieapp.di

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.vahitkeskin.movieapp.BuildConfig
import com.vahitkeskin.movieapp.api.MovieService
import com.vahitkeskin.movieapp.util.Contains
import com.vahitkeskin.movieapp.util.FlipperNetworkObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        apiUrl: String
    ): Retrofit {
        return Retrofit.Builder().baseUrl(apiUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient).build()
    }

    @Provides
    fun provideStackoverflowBaseUrl(): String = Contains.BASE_URL

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun client(): FlipperOkhttpInterceptor {
        return if (BuildConfig.DEBUG && FlipperNetworkObject.networkFlipperPlugin != null) {
            FlipperOkhttpInterceptor(FlipperNetworkObject.networkFlipperPlugin)
        } else {
            FlipperOkhttpInterceptor(null)
        }
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}

