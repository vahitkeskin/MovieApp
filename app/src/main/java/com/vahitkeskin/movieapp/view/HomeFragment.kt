package com.vahitkeskin.movieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.vahitkeskin.movieapp.adapter.MovieListAdapter
import com.vahitkeskin.movieapp.adapter.MovieLoadStateAdapter
import com.vahitkeskin.movieapp.adapter.SliderAdapter
import com.vahitkeskin.movieapp.databinding.FragmentHomeBinding
import com.vahitkeskin.movieapp.model.now_playing.ListResult
import com.vahitkeskin.movieapp.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private lateinit var movieListAdapter: MovieListAdapter
    private val TAG = this.javaClass.simpleName
    private var listResult = ArrayList<ListResult>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDetailViewModel.nowPlaying.observe(viewLifecycleOwner) {
            it.results.forEach { list ->
                listResult.add(list)
                val sliderAdapter = SliderAdapter(listResult)
                binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
                binding.imageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
                binding.imageSlider.startAutoCycle()
                binding.imageSlider.setSliderAdapter(sliderAdapter)
            }
        }

        movieListAdapter = MovieListAdapter(
            onClickMovieItem = { listResult ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(listResult = listResult)
                Navigation.findNavController(view).navigate(action)
            }
        )
        binding.rvMovieList.adapter = movieListAdapter.withLoadStateFooter(
            footer = MovieLoadStateAdapter(movieListAdapter)
        )
        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
        initObserver()
    }

    private fun initObserver() {
        movieDetailViewModel.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                getList.collectLatest {
                    movieListAdapter.submitData(it)
                }
            }
        }
    }
}