package com.vahitkeskin.movieapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vahitkeskin.movieapp.api.MovieService
import com.vahitkeskin.movieapp.model.now_playing.ListResult
import com.vahitkeskin.movieapp.model.now_playing.NowPlayingResponse
import com.vahitkeskin.movieapp.paging.MoviePagingAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */

class MovieRepository @Inject constructor(
    private val movieService: MovieService
) {
    fun getList(): Flow<NowPlayingResponse> {
        return flow {
            emit(movieService.nowPlayingList())
        }
    }

    fun getMovieList(): Flow<PagingData<ListResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingAdapter(movieService) }
        ).flow
    }
}