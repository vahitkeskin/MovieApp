package com.vahitkeskin.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vahitkeskin.movieapp.model.ListResult
import com.vahitkeskin.movieapp.model.NowPlayingResponse
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

    private var _nowPlaying = MutableLiveData<NowPlayingResponse>()
    var nowPlaying: LiveData<NowPlayingResponse> = _nowPlaying

    init {
        getNowPlaying()
    }

    private fun getNowPlaying() {
        viewModelScope.launch {
            movieRepository.sliderList().collect {
                _nowPlaying.postValue(it)
            }
        }
    }

    val getList: Flow<PagingData<ListResult>> = movieRepository.getMovieList().cachedIn(viewModelScope)
}