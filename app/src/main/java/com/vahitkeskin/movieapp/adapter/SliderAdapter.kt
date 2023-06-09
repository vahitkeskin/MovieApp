package com.vahitkeskin.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.smarteist.autoimageslider.SliderViewAdapter
import com.vahitkeskin.movieapp.databinding.SliderItemBinding
import com.vahitkeskin.movieapp.model.ListResult
import com.vahitkeskin.movieapp.util.Contains
import com.vahitkeskin.movieapp.util.loadImage

/**
 * @authot: Vahit Keskin
 * creared on 26.03.2023
 */

class SliderAdapter(
    private val list: ArrayList<ListResult>,
    private val onClickMovieItem: (image: ListResult) -> Unit
) : SliderViewAdapter<SliderAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        val itemBinding = SliderItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(itemBinding)
    }

    override fun onBindViewHolder(viewHolder: Holder, position: Int) {
        viewHolder.bind(list[position])
    }

    override fun getCount(): Int {
        return list.size
    }

    inner class Holder(private val binding: SliderItemBinding) : ViewHolder(binding.root) {
        fun bind(listResult: ListResult) {
            binding.imageView.loadImage(Contains.IMAGE_URL_BASE_URL + listResult.backdrop_path)
            binding.tvTitle.text = listResult.title
            binding.tvOverview.text = listResult.overview
            binding.root.setOnClickListener {
                onClickMovieItem.invoke(listResult)
            }
        }
    }
}