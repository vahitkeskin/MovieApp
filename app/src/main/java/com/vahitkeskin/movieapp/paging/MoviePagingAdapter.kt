package com.vahitkeskin.movieapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vahitkeskin.movieapp.api.MovieService
import com.vahitkeskin.movieapp.model.now_playing.ListResult
import com.vahitkeskin.movieapp.util.Contains.DEFAULT_PAGE
import java.io.IOException
import javax.inject.Inject

/**
 * @authot: Vahit Keskin
 * creared on 26.03.2023
 */
class MoviePagingAdapter @Inject constructor(
    private val movieService: MovieService
) : PagingSource<Int, ListResult>() {

    override fun getRefreshKey(state: PagingState<Int, ListResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListResult> {
        val position = params.key ?: DEFAULT_PAGE
        return try {
            val response = movieService.upcomingList(page = position)
            val list = response.results
            LoadResult.Page(
                data = list,
                prevKey = if (position == DEFAULT_PAGE) null else position,
                nextKey = if (list.isEmpty()) null else position + 1
            )
        } catch (exc: IOException) {
            return LoadResult.Error(exc)
        } catch (exc: IOException) {
            return LoadResult.Error(exc)
        }
    }

}