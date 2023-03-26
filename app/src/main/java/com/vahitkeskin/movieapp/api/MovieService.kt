package com.vahitkeskin.movieapp.api

import com.vahitkeskin.movieapp.model.NowPlayingResponse
import com.vahitkeskin.movieapp.util.Contains.API_KEY
import com.vahitkeskin.movieapp.util.Contains.DEFAULT_PAGE
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */

interface MovieService {

    @GET("/3/movie/now_playing")
    suspend fun nowPlayingSlider(@Query("api_key") api_key: String = API_KEY): NowPlayingResponse

    //Pagination
    @GET("/3/movie/upcoming")
    suspend fun upcomingList(
        @Query("api_key") api_key: String = API_KEY,
        @Query("page") page: Int = DEFAULT_PAGE,
    ): NowPlayingResponse

}