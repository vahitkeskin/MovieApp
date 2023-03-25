package com.vahitkeskin.movieapp.api

import com.vahitkeskin.movieapp.model.now_playing.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */

interface MovieService {

    @GET("/3/movie/now_playing")
    suspend fun fetchKeywords(): Response<NowPlayingResponse>

}