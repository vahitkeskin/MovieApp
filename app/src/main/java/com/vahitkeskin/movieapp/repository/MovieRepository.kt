package com.vahitkeskin.movieapp.repository

import androidx.annotation.WorkerThread
import com.vahitkeskin.movieapp.api.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */

class MovieRepository constructor(
    private val movieService: MovieService
) : Repository {

    @WorkerThread
    fun loadKeywordList() = flow {
        val response = movieService.fetchKeywords()
        emit(response)
    }.flowOn(Dispatchers.IO)
}