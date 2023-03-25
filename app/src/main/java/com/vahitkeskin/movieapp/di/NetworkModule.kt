package com.vahitkeskin.movieapp.di

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.vahitkeskin.movieapp.BuildConfig
import com.vahitkeskin.movieapp.api.MovieService
import com.vahitkeskin.movieapp.util.Contains.API_TIMEOUT
import com.vahitkeskin.movieapp.util.Contains.BASE_URL
import com.vahitkeskin.movieapp.util.FlipperNetworkObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private var retrofit: MovieService? = null

    @Singleton
    @Provides
    //init client with base url
    fun getClient(): MovieService {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client(OkHttpClient()))
                .build()
                .create(MovieService::class.java)
        }
        return retrofit as MovieService
    }

    @Provides
    @Singleton
    fun client(okHttpClient: OkHttpClient): OkHttpClient {
        return if (BuildConfig.DEBUG && FlipperNetworkObject.networkFlipperPlugin != null) {
            okHttpClient.newBuilder().addNetworkInterceptor(
                interceptor = FlipperOkhttpInterceptor(FlipperNetworkObject.networkFlipperPlugin)
            ).build()
        } else {
            with(okHttpClient.newBuilder()) {
                callTimeout(API_TIMEOUT, TimeUnit.SECONDS)
                connectTimeout(API_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(API_TIMEOUT, TimeUnit.SECONDS)
                this.build()
            }
        }
    }
}

