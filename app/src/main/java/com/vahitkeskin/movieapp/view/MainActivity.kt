package com.vahitkeskin.movieapp.view

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.vahitkeskin.movieapp.adapter.MovieListAdapter
import com.vahitkeskin.movieapp.model.MovieListModel
import com.vahitkeskin.movieapp.databinding.ActivityMainBinding

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Light Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Status Bar Transparent
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

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

        val movieListAdapter = MovieListAdapter(movieListModel)
        binding.rvMovieList.adapter = movieListAdapter
        binding.rvMovieList.layoutManager = LinearLayoutManager(this)
    }
}