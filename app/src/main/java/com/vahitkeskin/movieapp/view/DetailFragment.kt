package com.vahitkeskin.movieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vahitkeskin.movieapp.databinding.FragmentDetailBinding
import com.vahitkeskin.movieapp.util.Contains
import com.vahitkeskin.movieapp.util.dateArrangement
import com.vahitkeskin.movieapp.util.loadImage

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val listResult = DetailFragmentArgs.fromBundle(it).listResult
            binding.ivDetail.loadImage(Contains.IMAGE_URL_BASE_URL + listResult.backdrop_path)
            binding.tvImdbPoint.text = listResult.vote_average.toString()
            binding.tvImdbDate.text = listResult.release_date.replace("-",".").dateArrangement()
            binding.tvTitle.text = listResult.title
            binding.tvDetail.text = listResult.overview
        }
    }
}