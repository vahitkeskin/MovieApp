package com.vahitkeskin.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.vahitkeskin.movieapp.model.now_playing.NowPlayingResponse
import com.vahitkeskin.movieapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val movieIdSharedFlow: MutableSharedFlow<NowPlayingResponse> = MutableSharedFlow(replay = 1)

    val keywordListFlow = movieIdSharedFlow.flatMapLatest {
        movieRepository.loadKeywordList()
    }

}