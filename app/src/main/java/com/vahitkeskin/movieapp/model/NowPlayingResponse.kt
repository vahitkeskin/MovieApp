package com.vahitkeskin.movieapp.model

data class NowPlayingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<ListResult>,
    val total_pages: Int,
    val total_results: Int
)