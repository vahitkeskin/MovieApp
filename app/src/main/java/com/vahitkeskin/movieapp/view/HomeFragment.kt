package com.vahitkeskin.movieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.vahitkeskin.movieapp.adapter.MovieListAdapter
import com.vahitkeskin.movieapp.databinding.FragmentHomeBinding
import com.vahitkeskin.movieapp.model.MovieListModel
import com.vahitkeskin.movieapp.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val vm: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            vm.keywordListFlow.collectLatest {
                println("Name for result: ${it.body()?.dates}")
            }
        }

        val movieListModel = ArrayList<MovieListModel>()
        movieListModel.add(
            MovieListModel(
                "https://images.immediate.co.uk/production/volatile/sites/3/2019/04/Avengers-Endgame-Banner-2-de7cf60.jpg?quality=90&resize=620,413",
                ""
            )
        )
        movieListModel.add(
            MovieListModel(
                "https://img.cinemablend.com/filter:scale/quill/3/7/0/0/8/e/37008e36e98cd75101cf1347396eac8534871a19.jpg?mw=600",
                ""
            )
        )
        movieListModel.add(
            MovieListModel(
                "https://www.adgully.com/img/800/201711/spider-man-homecoming-banner.jpg",
                ""
            )
        )
        movieListModel.add(
            MovieListModel(
                "https://live.staticflickr.com/1980/29996141587_7886795726_b.jpg",
                ""
            )
        )

        val imageList = ArrayList<SlideModel>()
        movieListModel.forEach {
            imageList.add(SlideModel(it.image, it.title))
        }
        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)


        val movieListAdapter = MovieListAdapter(
            paymentList = movieListModel,
            onClickMovieItem = { image ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(demoImage = image)
                Navigation.findNavController(view).navigate(action)
            }
        )
        binding.rvMovieList.adapter = movieListAdapter
        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
    }
}