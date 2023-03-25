package com.vahitkeskin.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vahitkeskin.movieapp.databinding.ItemMovieListBinding
import com.vahitkeskin.movieapp.model.now_playing.ListResult
import com.vahitkeskin.movieapp.util.loadImage

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */
class MovieListAdapter(
    private val onClickMovieItem: (image: String) -> Unit
) : PagingDataAdapter<ListResult, MovieListAdapter.MovieListViewHolder>(LIST_COMPARATOR) {

    companion object {
        private val LIST_COMPARATOR = object : DiffUtil.ItemCallback<ListResult>() {
            override fun areItemsTheSame(oldItem: ListResult, newItem: ListResult): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ListResult, newItem: ListResult): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val itemBinding = ItemMovieListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val listResult = getItem(position) as ListResult
        holder.itemView.rootView.setOnClickListener {
            onClickMovieItem.invoke(listResult.backdrop_path)
        }
        holder.bind(listResult)
    }

    class MovieListViewHolder(private val itemBinding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(listResult: ListResult) {
            //https://www.themoviedb.org/t/p/w440_and_h660_face/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg
            val url = "https://www.themoviedb.org" + "/t/p/w440_and_h660_face" + listResult.backdrop_path
            println("Vahit abi naber ya: $url")
            itemBinding.ivListItem loadImage (url)
            itemBinding.tvList.text = listResult.title
        }
    }
}