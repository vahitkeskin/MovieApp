package com.vahitkeskin.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vahitkeskin.movieapp.model.now_playing.ListResult
import com.vahitkeskin.movieapp.model.now_playing.NowPlayingResponse
import com.vahitkeskin.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _movie = MutableLiveData<NowPlayingResponse>()
    var movie: LiveData<NowPlayingResponse> = _movie

    init {
        getListViewModel()
    }

    private fun getListViewModel() {
        viewModelScope.launch {
            movieRepository.getList().collect {
                _movie.postValue(it)
            }
        }
    }

    val getList: Flow<PagingData<ListResult>> = movieRepository.getMovieList().cachedIn(viewModelScope)
}